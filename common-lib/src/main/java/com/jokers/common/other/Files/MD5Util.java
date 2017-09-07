package com.jokers.common.other.Files;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yuton
 * @version 1.0
 * @description md5加密
 * @since 2017/2/7 18:04
 */
public class MD5Util {

    /**
     * @param fields {@link String []}
     * @return md5
     * @description MD5 33位加密
     */
    public static String getMD5Encode(String... fields) {
        if (ArrayUtils.isEmpty(fields)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(60);
        for (String field : fields) {
            if (!StringUtils.isEmpty(field)) {
                builder.append(field);
            }
        }
        return DigestUtils.md5Hex(builder.toString());
    }
}
