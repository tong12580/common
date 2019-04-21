package com.jokers.common.other.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;

/**
 * <p>SerializerUtils</p>
 * <span>系列化</span>
 *
 * @author yuTong
 * @version 1.0
 * @since 2019/4/9 23:51
 */
public class SerializerUtils {

    private static final ThreadLocal<LinkedBuffer> BUFFERS = new ThreadLocal<>();
    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.getSchema(SerializeDeserializeWrapper.class);

    /**
     * 简单对象的序列化
     *
     * @param t   T
     * @param <T> 泛型
     * @return 序列化数组
     */
    public static <T> byte[] serializerByProtoSingle(T t) {
        LinkedBuffer buffer = BUFFERS.get();
        if (null == buffer) {
            buffer = LinkedBuffer.allocate();
            BUFFERS.set(buffer);
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = RuntimeSchema.getSchema((Class<T>) t.getClass());
        byte[] bytes = ProtobufIOUtil.toByteArray(t, schema, buffer);
        buffer.clear();
        return bytes;
    }

    /**
     * 复杂对象（List,Map等）的序列化
     *
     * @param t   T
     * @param <T> 泛型
     * @return 序列化数组
     */
    public static <T> byte[] serializerByProto(T t) {
        LinkedBuffer buffer = BUFFERS.get();
        if (null == buffer) {
            buffer = LinkedBuffer.allocate();
            BUFFERS.set(buffer);
        }
        SerializeDeserializeWrapper<T> wrapper = SerializeDeserializeWrapper.builder(t);
        byte[] bytes = ProtobufIOUtil.toByteArray(wrapper, WRAPPER_SCHEMA, buffer);
        buffer.clear();
        return bytes;
    }

    /**
     * 简单对象的反序列化
     *
     * @param bytes  字节码
     * @param tClass 泛型指示
     * @param <T>    泛型
     * @return 对象
     */
    public static <T> T deserializerByProtoSingle(byte[] bytes, Class<T> tClass) {
        Schema<T> schema = RuntimeSchema.getSchema(tClass);
        T t = schema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, t, schema);
        return t;
    }

    /**
     * 复杂对象的反序列化
     *
     * @param bytes 字节码
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T deserializerByProto(byte[] bytes) {
        SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
        ProtobufIOUtil.mergeFrom(bytes, wrapper, WRAPPER_SCHEMA);
        return wrapper.getData();
    }

    /**
     * 封装参数
     *
     * @param <T> 泛型指示
     */
    static class SerializeDeserializeWrapper<T> implements Serializable {
        private T data;

        T getData() {
            return data;
        }

        void setData(T data) {
            this.data = data;
        }

        static <T> SerializeDeserializeWrapper<T> builder(T t) {
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
            wrapper.setData(t);
            return wrapper;
        }
    }

}
