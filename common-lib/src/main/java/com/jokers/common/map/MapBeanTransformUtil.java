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
 * @author yutong
 * @version 1.0
 *
 * @since 2018/2/19 14:16
 */
public class MapBeanTransformUtil {

    /**
     * @param bean Object
     * @return {@link Map}
     *  toMap
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

    /***
     * Map转Bean
     * @param tClass {@link Class<T>}
     * @param map {@link Map}
     * @param <T> T
     * @return T
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
