package com.jokers.common.map;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ManagedConcurrentWeakHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    private final ConcurrentMap<ManagedConcurrentWeakHashMap.Key, V> map = new ConcurrentHashMap<>();
    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    public ManagedConcurrentWeakHashMap() {
    }

    public void maintain() {
        ManagedConcurrentWeakHashMap.Key key;
        while ((key = (ManagedConcurrentWeakHashMap.Key) this.queue.poll()) != null) {
            if (!key.isDead()) {
                key.ackDeath();
                this.map.remove(key);
            }
        }

    }

    private ManagedConcurrentWeakHashMap.Key createStoreKey(Object key) {
        return new ManagedConcurrentWeakHashMap.Key(key, this.queue);
    }

    private ManagedConcurrentWeakHashMap.Key createLookupKey(Object key) {
        return new ManagedConcurrentWeakHashMap.Key(key, null);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return value != null && this.map.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        return key != null && this.map.containsKey(this.createLookupKey(key));
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            public boolean isEmpty() {
                return ManagedConcurrentWeakHashMap.this.map.isEmpty();
            }

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    private final Iterator<Entry<ManagedConcurrentWeakHashMap.Key, V>> it;

                    {
                        this.it = ManagedConcurrentWeakHashMap.this.map.entrySet().iterator();
                    }

                    @Override
                    public boolean hasNext() {
                        return this.it.hasNext();
                    }

                    @Override
                    public Entry<K, V> next() {
                        return new Entry<K, V>() {
                            private final Entry<ManagedConcurrentWeakHashMap.Key, V> en;

                            {
                                this.en = it.next();
                            }

                            @Override
                            @SuppressWarnings("unchecked")
                            public K getKey() {
                                return (K) ((Key) this.en.getKey()).get();
                            }

                            @Override
                            public V getValue() {
                                return this.en.getValue();
                            }

                            @Override
                            public V setValue(V value) {
                                Objects.requireNonNull(value);
                                return this.en.setValue(value);
                            }
                        };
                    }

                    @Override
                    public void remove() {
                        this.it.remove();
                    }
                };
            }

            @Override
            public int size() {
                return ManagedConcurrentWeakHashMap.this.map.size();
            }
        };
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                continue;
            }
            action.accept(k, v);
        }
    }

    @Override
    public V get(Object key) {
        return key == null ? null : this.map.get(this.createLookupKey(key));
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(value);
        return this.map.put(this.createStoreKey(key), value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        remove(key);
        return null == get(key);
    }

    @Override
    public V remove(Object key) {
        return this.map.remove(this.createLookupKey(key));
    }

    @Override
    public void clear() {
        this.map.clear();
        this.maintain();
    }

    public V putIfAbsent(K key, V value) {
        Objects.requireNonNull(value);
        ManagedConcurrentWeakHashMap.Key storeKey = this.createStoreKey(key);
        V oldValue = this.map.putIfAbsent(storeKey, value);
        if (oldValue != null) {
            storeKey.ackDeath();
        }

        return oldValue;
    }


    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        Objects.requireNonNull(newValue);
        return this.map.replace(this.createLookupKey(key), oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        Objects.requireNonNull(value);
        return this.map.replace(this.createLookupKey(key), value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v, newValue;
        return ((v = get(key)) == null &&
                (newValue = mappingFunction.apply(key)) != null &&
                (v = putIfAbsent(key, newValue)) == null) ? newValue : v;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        while ((oldValue = get(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                if (replace(key, oldValue, newValue))
                    return newValue;
            } else if (remove(key, oldValue))
                return null;
        }
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);
        for (; ; ) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue == null) {
                // delete mapping
                if (oldValue != null || containsKey(key)) {
                    // something to remove
                    if (remove(key, oldValue)) {
                        // removed the old value as expected
                        return null;
                    }

                    // some other value replaced old value. try again.
                    oldValue = get(key);
                } else {
                    // nothing to do. Leave things as they were.
                    return null;
                }
            } else {
                // add or replace old mapping
                if (oldValue != null) {
                    // replace
                    if (replace(key, oldValue, newValue)) {
                        // replaced as expected.
                        return newValue;
                    }

                    // some other value replaced old value. try again.
                    oldValue = get(key);
                } else {
                    // add (replace if oldValue was null)
                    if ((oldValue = putIfAbsent(key, newValue)) == null) {
                        // replaced
                        return newValue;
                    }

                    // some other value replaced old value. try again.
                }
            }
        }
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        for (; ; ) {
            if (oldValue != null) {
                V newValue = remappingFunction.apply(oldValue, value);
                if (newValue != null) {
                    if (replace(key, oldValue, newValue))
                        return newValue;
                } else if (remove(key, oldValue)) {
                    return null;
                }
                oldValue = get(key);
            } else {
                if ((oldValue = putIfAbsent(key, value)) == null) {
                    return value;
                }
            }
        }
    }

    private static class Key extends WeakReference<Object> {
        private final int hash;
        private boolean dead;

        Key(Object key, ReferenceQueue<Object> queue) {
            super(key, queue);
            this.hash = key.hashCode();
        }

        @Override
        public int hashCode() {
            return this.hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (this.dead) {
                return false;
            } else if (!(obj instanceof Reference)) {
                return false;
            } else {
                Object oA = this.get();
                Object oB = ((Reference) obj).get();
                return oA == oB || (oA != null && oB != null) && oA.equals(oB);
            }
        }

        void ackDeath() {
            this.dead = true;
        }

        boolean isDead() {
            return this.dead;
        }
    }
}
