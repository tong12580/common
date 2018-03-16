package com.jokers.common.cache;

import com.jokers.common.map.ManagedConcurrentWeakHashMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * NettyCache
 * Created by yuTong on 2018/03/16.
 */
public class NettyCachePool {
    private volatile static ManagedConcurrentWeakHashMap<String, Channel> map = new ManagedConcurrentWeakHashMap<>();
    private NettyCachePool() {
    }

    public static NettyCachePool getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public void put(String clientId, Channel channel) {
        if (null != channel && StringUtils.isNotEmpty(clientId)) {
            map.put(clientId, channel);
        }
    }
    public Channel get(String clientId) {
        return map.get(clientId);
    }

    public Map<String, Channel> getAllMap() {
        return map;
    }

    public void remove(ChannelHandlerContext channel) {
        String clientId = null;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(channel)) {
                clientId = (String) entry.getKey();
                break;
            }
        }
        if (null != clientId) {
            remove(clientId);
        }
    }

    private void remove(String clientId) {
        map.remove(clientId);
        map.maintain();
    }

    private enum Singleton {
        INSTANCE;
        private NettyCachePool nettyCachePool;

        Singleton() {
            nettyCachePool = new NettyCachePool();
        }

        public NettyCachePool getInstance() {
            return nettyCachePool;
        }
    }
}
