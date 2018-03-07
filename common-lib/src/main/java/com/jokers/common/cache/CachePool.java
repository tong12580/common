package com.jokers.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;

/**
 * <p>WeakHashMap缓存实现.</p>
 *
 * @version 1.0
 * @author yuton
 */
public class CachePool implements Cache {
    private static CachePool cachePool = new CachePool();
    private volatile static Map<Object, Object> cacheItems = new WeakHashMap<>();

    private CachePool() {
    }

    /**
     * <p>builder.</p>
     *
     * @return a {@link com.jokers.common.cache.CachePool} object.
     */
    public static CachePool builder() {
        return cachePool;
    }

    /**
     * <p>size.</p>
     *
     * @return a int.
     */
    public int size() {
        return cacheItems.size();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Object getNativeCache() {
        return cacheItems;
    }

    /** {@inheritDoc} */
    @Override
    public ValueWrapper get(Object o) {
        Object value = cacheItems.get(o);
        return toWrapper(value);
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object o, Class<T> aClass) {
        if (cacheItems.containsKey(o)) {
            return (T) cacheItems.get(o);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void put(Object o, Object o1) {
        cacheItems.put(o, o1);
    }

    /** {@inheritDoc} */
    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void evict(Object o) {
        if (cacheItems.containsKey(o)) {
            cacheItems.remove(o);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        cacheItems.clear();
    }

    private static final Object NULL_HOLDER = new NullHolder();

    private static class NullHolder implements Serializable {
    }

    private ValueWrapper toWrapper(Object value) {
        return (value != null ? new SimpleValueWrapper(fromStoreValue(value)) : null);
    }

    private Object fromStoreValue(Object storeValue) {
        if (storeValue == NULL_HOLDER) {
            return null;
        }
        return storeValue;
    }
}
