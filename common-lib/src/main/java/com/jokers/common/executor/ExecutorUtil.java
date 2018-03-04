package com.jokers.common.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>ExecutorUtil class.</p>
 *
 * @author yuton
 * @version 1.0
 * @since 2017/7/19 11:54
 */
public class ExecutorUtil {
    /**
     * <p>builder.</p>
     *
     * @return a {@link java.util.concurrent.Executor} object.
     */
    public static Executor builder() {
        return builder(5, 500, 600, 10);
    }

    /**
     * <p>builder.</p>
     *
     * @param corePoolSize a int.
     * @param maxPoolSize a int.
     * @param queueCapacity a int.
     * @param keepAliveSeconds a int.
     * @return a {@link java.util.concurrent.Executor} object.
     */
    public static Executor builder(int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
