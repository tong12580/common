package com.jokers.common.other.Files;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * <p>Base64Util class.</p>
 *
 * @author yuton
 * @since 2016/9/29 9:56
 *  Base64加密
 * @version 1.0
 */
public class Base64Util {

    /**
     * <p>decodeBase64.</p>
     *
     * @param base64String a {@link java.lang.String} object.
     * @param encoding a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.UnsupportedEncodingException if any.
     */
    public static String decodeBase64(String base64String, String encoding) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(base64String)) {
            return null;
        }
        byte[] decodeArray = Base64.decodeBase64(base64String);
        return new String(decodeArray, encoding);
    }

    /**
     * <p>encodeBase64.</p>
     *
     * @param stringValue a {@link java.lang.String} object.
     * @param encoding a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.UnsupportedEncodingException if any.
     */
    public static String encodeBase64(String stringValue, String encoding) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(stringValue)) {
            return null;
        }
        return Base64.encodeBase64String(stringValue.getBytes(Charset.forName(encoding)));
    }
}
