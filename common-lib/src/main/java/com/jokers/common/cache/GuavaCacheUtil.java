package com.jokers.common.cache;

import com.jokers.common.json.JsonUtil;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yuton
 * @version 1.0
 * @description 手工缓存实现
 * @since 2016/12/2 14:43
 */
@Slf4j
public class GuavaCacheUtil implements Cache {
    private static final Object NULL_HOLDER = new NullHolder();
    private static final String DEFAULT_NAME = "system_cache";
    private final String name;
    private final com.google.common.cache.Cache<Object, Object> cache;
    private final boolean allowNullValues;
    private static GuavaCacheUtil guavaCacheUtil = new GuavaCacheUtil();

    public static GuavaCacheUtil builder() {
        return guavaCacheUtil;
    }

    private GuavaCacheUtil() {
        this(DEFAULT_NAME, CacheBuilder.newBuilder().maximumSize(500).expireAfterWrite(10, TimeUnit.MINUTES).build());
    }

    private GuavaCacheUtil(String name, com.google.common.cache.Cache<Object, Object> cache) {
        this(name, cache, true);
    }

    private GuavaCacheUtil(String name, com.google.common.cache.Cache<Object, Object> cache, boolean allowNullValues) {
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(cache, "Cache must not be null");
        this.name = name;
        this.cache = cache;
        this.allowNullValues = allowNullValues;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        key = getKey(key.toString());
        Object value = this.cache.getIfPresent(key);
        log.debug("getKey = {}, getObject = {}", key, JsonUtil.objectToJson(value));
        return toWrapper(value);
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        key = getKey(key.toString());
        T value = fromStoreValue(this.cache.getIfPresent(key), aClass);
        log.debug("getKey = {}, getObject = {}", key, JsonUtil.objectToJson(value));
        return value;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        key = getKey(key.toString());
        this.cache.put(key, toStoreValue(value));
        log.debug("getKey = {}, getObject = {}", key, JsonUtil.objectToJson(value));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        PutIfAbsentCallable callable = new PutIfAbsentCallable(value);
        try {
            Object result = this.cache.get(key, callable);
            return (callable.called ? null : toWrapper(result));
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
        log.debug("deleteKey = {}", key);
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
        log.debug("clearAll");
    }

    private ValueWrapper toWrapper(Object value) {
        return (value != null ? new SimpleValueWrapper(fromStoreValue(value, null)) : null);
    }

    private static class NullHolder implements Serializable {
    }

    private Object toStoreValue(Object userValue) {
        if (this.allowNullValues && userValue == null) {
            return NULL_HOLDER;
        }
        return userValue;
    }

    @SuppressWarnings("unchecked")
    private <T> T fromStoreValue(Object storeValue, Class<T> aClass) {
        if (this.allowNullValues && storeValue == NULL_HOLDER) {
            return null;
        }
        if (storeValue != null && aClass != null && !aClass.isInstance(storeValue)) {
            throw new IllegalStateException("Cached value is not of required type [" + aClass.getName() + "]: " + storeValue);
        }
        return (T) storeValue;
    }


    private class PutIfAbsentCallable implements Callable<Object> {

        private final Object value;

        private boolean called;

        PutIfAbsentCallable(Object value) {
            this.value = value;
        }

        @Override
        public Object call() throws Exception {
            this.called = true;
            return toStoreValue(this.value);
        }
    }

    private String getKey(String key) {
        return name + ":" + key;
    }
}
