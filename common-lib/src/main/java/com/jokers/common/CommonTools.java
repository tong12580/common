package com.jokers.common;

import com.jokers.common.http.HttpUtil;
import com.jokers.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * @author yuton
 * @version 1.0
 * 公共校验方法
 * @since 2016/8/2 12:06
 */
@Slf4j
public class CommonTools {
    private static final String PATTERN_HAVE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 遍历Map并除去空值
     *
     * @param reqMap reqMap {@link Map}
     * @return reqMap {@link Map}
     */
    public static <K, V> Map<K, V> checkParamsAndDelEmpty(Map<K, V> reqMap) {
        reqMap.entrySet().stream().filter(map -> null == map.getValue()).forEach(map -> reqMap.remove(map.getKey()));
        return reqMap;
    }

    /***
     * 判断字符串是否只有数字及字母
     * @param str String
     * @return String
     */
    public static boolean isNumOrChar(String str) {
        return str.matches("^[a-z0-9A-Z]+$");
    }

    /***
     * 判断字符串是否只含有中文
     * @param str String
     * @return boolean
     */
    public static boolean isChineseChar(String str) {
        return str.matches("[\\u4E00-\\u9FBF]+$");
    }

    /**
     * @param n int
     * @return String
     * 生成若干位随机数字字符
     */
    public static String getRandomNum(int n) {
        return RandomStringUtils.randomNumeric(n);
    }

    /***
     * 随机若干位字符串只含字母
     * @param n String
     * @return String
     */
    public static String getRandomStr(int n) {
        return RandomStringUtils.randomAlphabetic(n);
    }

    /***
     * 检查一个数组中是否包含某个特定的值
     * @param arr String []
     * @param targetValue String []
     * @return boolean
     */
    public static boolean useLoop(String[] arr, String targetValue) {
        for (String s : arr) {
            if (s.equals(targetValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param str String
     * @return boolean
     * 判断字符串是否为数字
     */
    public static boolean isNumber(String str) {
        return str.matches("[0-9]*");
    }

    /**
     * @param phone String
     * @return boolean
     * 判断手机号是否合法
     */
    public static boolean isPhone(String phone) {
        return StringUtils.isNotBlank(phone) && phone.matches("^[1][3,4,5,7,8][0-9]{9}$");
    }

    /**
     * @param email String
     * @return boolean
     * 判断邮箱号是否合法
     */
    public static boolean isEmail(String email) {
        return email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * @param name String
     * @return String
     * 获取文件后缀，返回如：.jpg
     */
    public static String getFileSuffix(String name) {
        int loc = name.lastIndexOf('.');
        if (loc != -1) {
            return name.substring(loc);
        }
        return null;
    }

    /**
     * @param path String
     * @return void
     * 创建目录，如果存在则不创建
     */
    public static boolean makeDir(String path) {
        return new File(path).mkdirs();
    }


    /***
     * 隐藏号码
     * @param param String
     * @return String
     */
    public static String putConcealParam(String param) {
        return param.substring(0, 3) + "****"
                + param.substring(param.length() - 4, param.length());
    }

    /***
     * json to bean
     * @param json String
     * @param tClass Class
     * @param <T> T
     * @return T
     */
    public static <T> T getBean(String json, Class<T> tClass) throws IOException {
        return JsonUtil.jsonToBean(json, tClass, PATTERN_HAVE_TIME);
    }

    /**
     * @return String
     * 获取本机公网ip
     */
    public static String getLocalPublicIp() {
        String html = HttpUtil.httpGet(HttpUtil.SELECT_PUBLIC_IP_ADDRESS);
        return html.substring(html.indexOf("[") + 1, html.indexOf("]"));
    }

    /**
     * @return String
     * 获取本机操作系统名称
     */
    public static String getLocalOS() {
        return SystemUtils.OS_NAME;
    }

    /**
     * @return Long
     * 获取当前系统中 JVM最大可用堆空间
     */
    public static Long getJVMUsableMemory() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return max - total + free;
    }

    /**
     * @param str String
     * @return String
     * 替换表情符号
     */
    public static String replaceEmoji(String str) {
        return str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
    }
}
