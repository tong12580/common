package com.jokers.common.map;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>MapBeanTransformUtil class.</p>
 *
 * @author yutong
 * @version 1.0
 * @since 2018/2/19 14:16
 */
public class MapBeanTransformUtil {

    /**
     * <p>beanToMap.</p>
     *
     * @param bean Object
     * @return Map
     *  toMap
     * @throws java.beans.IntrospectionException if any.
     * @throws java.lang.reflect.InvocationTargetException if any.
     * @throws java.lang.IllegalAccessException if any.
     */
    public static Map<String, Object> beanToMap(Object bean)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    }
                }
            }
        }
        return returnMap;
    }

    /**
     *
     * Map转Bean
     *
     * @param tClass Class
     * @param map Map
     * @param <T> T
     * @return T
     * @throws java.beans.IntrospectionException if any.
     * @throws java.lang.IllegalAccessException if any.
     * @throws java.lang.InstantiationException if any.
     * @throws java.lang.reflect.InvocationTargetException if any.
     */
    public static <T> T mapToBean(Class<T> tClass, Map<String, Object> map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
        T bean = tClass.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            String propertyName; // javaBean属性名
            Object propertyValue; // javaBean属性值
            for (PropertyDescriptor pd : propertyDescriptors) {
                propertyName = pd.getName();
                if (map.containsKey(propertyName)) {
                    propertyValue = map.get(propertyName);
                    pd.getWriteMethod().invoke(bean, propertyValue);
                }
            }
            return bean;
        }
        return null;
    }
}
