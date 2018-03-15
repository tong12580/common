package com.jokers.common.position;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>进制转换</p>
 *
 * @author 刘志强
 * @author yuton 校对
 * @version 1.0
 * @since 2017/05/23
 */
public class DigitalTransUtil {
    private static String[] binaryArray = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    private static String _HEX = "0123456789ABCDEF";

    /**
     * 十六进制转十进制
     *
     * @param hex String
     * @return String
     */
    public static String hexChangeDecimal(String hex) {
        return String.valueOf(hexChangeDecimalLong(hex));
    }

    /**
     * <p>hexChangeDecimalInt.</p>
     *
     * @param hex a {@link java.lang.String} object.
     * @return a int.
     */
    public static int hexChangeDecimalInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    /**
     * <p>hexChangeDecimalLong.</p>
     *
     * @param hex a {@link java.lang.String} object.
     * @return a long.
     */
    public static long hexChangeDecimalLong(String hex) {
        return Long.parseLong(hex, 16);
    }


    /**
     * 将十进制转换为指定长度的十六进制字符串
     *
     * @param algorithm int 十进制数字
     * @param maxLength int 转换后的十六进制字符串长度
     * @return String 转换后的十六进制字符串
     */
    public static String algorithmToHEXString(int algorithm, int maxLength) {
        String result;
        result = Integer.toHexString(algorithm);
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return patchHexString(result.toUpperCase(), maxLength);
    }

    /**
     * 字节数组转为普通字符串（ASCII对应的字符）
     *
     * @param byteArray byte[]
     * @return String
     */
    public static String bytetoString(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        char temp;
        for (byte aByteArray : byteArray) {
            temp = (char) aByteArray;
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * 二进制字符串转十进制
     *
     * @param binary 二进制字符串
     * @return 十进制数值
     */
    public static int binaryToAlgorithm(String binary) {
        int max = binary.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = binary.charAt(i - 1);
            int algorithm = c - '0';
            result += Math.pow(2, max - i) * algorithm;
        }
        return result;
    }

    /**
     * 十进制转十六进制 保证位数为4位
     *
     * @param decimal String
     * @return String
     * @author yuTong
     */
    public static String decimalChangeHex(int decimal) {
        String result = Integer.toHexString(decimal);
        int k = 4 - result.length();
        result = StringUtils.repeat("0", k) + result;
        return result;
    }

    /**
     * 十进制转换为十六进制字符串
     *
     * @param algorithm int 十进制的数字
     * @return String 对应的十六进制字符串
     */
    public static String algorithmToHEXString(int algorithm) {
        String result;
        result = Integer.toHexString(algorithm);

        if (result.length() % 2 == 1) {
            result = String.format("0%s", result);

        }
        result = result.toUpperCase();

        return result;
    }

    /**
     * HEX字符串前补0，主要用于长度位数不足。
     *
     * @param str       String 需要补充长度的十六进制字符串
     * @param maxLength int 补充后十六进制字符串的长度
     * @return 补充结果
     */
    public static String patchHexString(String str, int maxLength) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < maxLength - str.length(); i++) {
            temp.insert(0, "0");
        }
        str = (temp + str).substring(0, maxLength);
        return str;
    }


    /**
     * 十六进制串转化为byte数组
     *
     * @param hex hex
     * @return the array of byte
     * @throws java.lang.IllegalArgumentException if any.
     */
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteInt = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteInt).byteValue();
        }
        return b;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static String byte2hex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte aB : b) {
            stmp = Integer.toHexString(aB & 0xff);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    /**
     * Convert byte[] to hex string. 把字节数组转化为字符串
     * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[]   把为字符串转化为字节数组
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) _HEX.indexOf(c);
    }

    /**
     * <p>amrDecode.</p>
     *
     * @param rawBytes an array of byte.
     * @return an array of byte.
     */
    public static byte[] amrDecode(byte[] rawBytes) {
        String hex = byte2hex(rawBytes);
        String tran = hex.replaceAll("[7][D][0][1]", "7D");
        tran = tran.replaceAll("[7][D][0][2]", "5B");
        tran = tran.replaceAll("[7][D][0][3]", "5D");
        tran = tran.replaceAll("[7][D][0][4]", "2C");
        tran = tran.replaceAll("[7][D][0][5]", "2A");
        return hex2byte(tran);
    }

    /**
     * <p>amrEncode.</p>
     *
     * @param rawBytes an array of byte.
     * @return an array of byte.
     */
    public static byte[] amrEncode(byte[] rawBytes) {
        String hex = byte2hex(rawBytes);
        StringBuilder tran = new StringBuilder();
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            if ("7D".equals(swap)) {
                swap = "7D01";
            }
            if ("5B".equals(swap)) {
                swap = "7D02";
            }
            if ("5D".equals(swap)) {
                swap = "7D03";
            }
            if ("2C".equals(swap)) {
                swap = "7D04";
            }
            if ("2A".equals(swap)) {
                swap = "7D05";
            }
            tran.append(swap);
        }
        return hex2byte(tran.toString());
    }

    /**
     * <p>byte2Base64String.</p>
     *
     * @param b an array of byte.
     * @return a {@link java.lang.String} object.
     */
    public static String byte2Base64String(byte[] b) {
        byte[] beforeBase64 = amrDecode(b);
        String base64 = Base64.encodeBase64String(beforeBase64);
        base64 = base64.replaceAll("\\n", "");
        base64 = base64.replaceAll("\\r", "");
        return base64;
    }

    /**
     * <p>base64String2byte.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @return an array of byte.
     */
    public static byte[] base64String2byte(String s) {
        s = s.replaceAll("\\n", "");
        s = s.replaceAll("\\r", "");
        byte[] beforeEncode = Base64.decodeBase64(s);
        return amrEncode(beforeEncode);
    }

    /**
     * <p>hexStr2BinStr.</p>
     *
     * @param hexString hexString
     * @return 将十六进制转换为二进制字符串   16-2
     */
    public static String hexStr2BinStr(String hexString) {
        return bytes2BinStr(hexStr2BinArr(hexString));
    }

    /**
     * <p>bytes2BinStr.</p>
     *
     * @param bArray 二进制数组
     * @return 二进制数组转换为二进制字符串   2-2
     */
    public static String bytes2BinStr(byte[] bArray) {
        StringBuilder outStr = new StringBuilder();
        int pos;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr.append(binaryArray[pos]);
            //低四位
            pos = b & 0x0F;
            outStr.append(binaryArray[pos]);
        }
        return outStr.toString();
    }

    /**
     * <p>hexStr2BinArr.</p>
     *
     * @param hexString 十六进制字符串
     * @return 将十六进制转换为二进制字节数组   16-2
     */
    public static byte[] hexStr2BinArr(String hexString) {
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high;//字节高四位
        byte low;//字节低四位
        for (int i = 0; i < len; i++) {
            //右移四位得到高位
            high = (byte) ((_HEX.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) _HEX.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);
        }
        return bytes;
    }

    /**
     * 中文转Unicode
     *
     * @param chinese 中文字符 无\\u分隔符
     * @return String
     */
    public static String chineseToUnicode(String chinese) {
        if (!chinese.matches("[\\u4E00-\\u9FBF]+$")) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chinese.length(); i++) {
            char c = chinese.charAt(i);
            builder.append(Integer.toHexString(c));
        }
        return builder.toString();
    }

    /**
     * 16进制ASCII转字符串
     *
     * @param hexStr 16进制Ascii码
     * @return String
     */
    public static String hexStr2Str(String hexStr) {
        char[] hexes = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = _HEX.indexOf(hexes[2 * i]) * 16;
            n += _HEX.indexOf(hexes[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 字符串转16进制Ascii码
     *
     * @param s String
     * @return hexString
     */
    public static String str2HexStr(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            sb.append(Integer.toHexString(aChar));
        }
        return sb.toString().toUpperCase().trim();
    }
}
