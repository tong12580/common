package com.jokers.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * <p>获取spring容器，以访问容器中定义的其他bean class.</p>
 *
 * @author yuton
 * @since May 6, 2011 2:35:22 PM
 * @version $Id: $Id
 */
@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * <p>getBean.</p>
     *
     * @param beanId String
     * @return Object
     * 获取对象
     * @throws org.springframework.beans.BeansException if any.
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

    /**
     * <p>getBean.</p>
     *
     * @param tClass a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a T object.
     * @throws org.springframework.beans.BeansException if any.
     */
    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }

    /** {@inheritDoc} */
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * <p>Getter for the field <code>applicationContext</code>.</p>
     *
     * @return a {@link org.springframework.context.ApplicationContext} object.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}

