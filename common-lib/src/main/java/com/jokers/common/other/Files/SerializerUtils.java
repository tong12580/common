package com.jokers.common.other.Files;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * <p>SerializerUtils</p>
 * <span>系列化</span>
 *
 * @author yuTong
 * @version 1.0
 * @since 2019/4/9 23:51
 */
public class SerializerUtils {

    public static <T> byte[] serializer(T t) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = RuntimeSchema.getSchema((Class<T>) t.getClass());
        return ProtobufIOUtil.toByteArray(t, schema, LinkedBuffer.allocate());
    }

    public static <T> T deserializer(byte[] bytes, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(tClass);
            ProtobufIOUtil.mergeFrom(bytes, t, schema);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
