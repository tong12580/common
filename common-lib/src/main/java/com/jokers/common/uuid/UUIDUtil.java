package com.jokers.common.uuid;

import java.util.UUID;

/**
 * @author yuton
 * @version 1.0
 *
 * @since 2017/2/8 10:48
 */
public class UUIDUtil {
    /***
     * UUid 字串
     */
    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    /**
     * @return String
     *  获取短8位UUid
     */
    public static String getShortUUid() {
        StringBuilder shortUUid = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortUUid.append(chars[x % 0x3E]);
        }
        return shortUUid.toString();
    }
}
