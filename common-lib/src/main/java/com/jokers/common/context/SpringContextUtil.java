package com.jokers.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yuton
 * @description 获取spring容器，以访问容器中定义的其他bean
 * @since May 6, 2011 2:35:22 PM
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private SpringContextUtil() {
    }

    /**
     * @param beanId String
     * @return Object
     * @description 获取对象
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}

