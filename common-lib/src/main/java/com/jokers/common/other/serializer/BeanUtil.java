package com.jokers.common.other.serializer;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * <p>BeanUtil</p>
 * <span>豆子转换</span>
 *
 * @author yuTong
 * @version 1.0
 * @since 2019/5/10 21:30
 */
public class BeanUtil {


    /**
     * 将新对象的有的新值的属性赋值给旧对象
     *
     * @param oldBean 老对象
     * @param newBean 新对象
     * @return T
     */
    public static <T> T copyProperties(T oldBean, T newBean) {
        Field[] fields = oldBean.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String name = field.getName();
                Field newFiled = newBean.getClass().getDeclaredField(name);
                newFiled.setAccessible(true);
                field.setAccessible(true);
                if (null == newFiled.get(newBean) || StringUtils.isBlank(newFiled.get(newBean).toString())) {
                    continue;
                }
                field.set(oldBean, newFiled.get(newBean));
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return oldBean;
    }
}