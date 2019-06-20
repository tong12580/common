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
 * <p>CommonTools class.</p>
 *
 * @author yuton
 * @version 1.0
 * 公共校验方法
 * @since 2016/8/2 12:06
 */
@Slf4j
public class CommonTools {
    private static final String PATTERN_HAVE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final int ELEVEN = 11;
    private static final int EIGHT = 8;

    /**
     * 遍历Map并除去空值
     *
     * @param reqMap reqMap
     * @param <K>    a K object.
     * @param <V>    a V object.
     * @return reqMap
     */
    public static <K, V> Map<K, V> checkParamsAndDelEmpty(Map<K, V> reqMap) {
        reqMap.entrySet().stream().filter(map -> null == map.getValue()).forEach(map -> reqMap.remove(map.getKey()));
        return reqMap;
    }

    /**
     * 判断字符串是否只有数字及字母
     *
     * @param str String
     * @return String
     */
    public static boolean isNumOrChar(String str) {
        return str.matches("^[a-z0-9A-Z]+$");
    }

    /**
     * 判断字符串是否只含有中文
     *
     * @param str String
     * @return boolean
     */
    public static boolean isChineseChar(String str) {
        return str.matches("[\\u4E00-\\u9FBF]+$");
    }

    /**
     * <p>生成若干位随机数字字符.</p>
     *
     * @param n int
     * @return String
     */
    public static String getRandomNum(int n) {
        return RandomStringUtils.randomNumeric(n);
    }

    /**
     * 随机若干位字符串只含字母
     *
     * @param n String
     * @return String
     */
    public static String getRandomStr(int n) {
        return RandomStringUtils.randomAlphabetic(n);
    }

    /**
     * 检查一个数组中是否包含某个特定的值
     *
     * @param arr         String []
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
     * <p>判断字符串是否为数字.</p>
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return str.matches("[0-9]*");
    }

    /**
     * <p>判断手机号是否合法.</p>
     *
     * @param phone String
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return StringUtils.isNotBlank(phone) && phone.matches("^[1][1-9][0-9]{9}$");
    }

    /**
     * <p>判断邮箱号是否合法.</p>
     *
     * @param email String
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * <p>获取文件后缀，返回如：.jpg</p>
     *
     * @param name String
     * @return String
     */
    public static String getFileSuffix(String name) {
        int loc = name.lastIndexOf('.');
        if (loc != -1) {
            return name.substring(loc);
        }
        return null;
    }

    /**
     * <p>创建目录，如果存在则不创建.</p>
     *
     * @param path String
     * @return void
     */
    public static boolean makeDir(String path) {
        return new File(path).mkdirs();
    }


    /**
     * 隐藏号码
     *
     * @param param String
     * @return String
     */
    public static String putConcealParam(String param) {
        return param.substring(0, 3) + "****"
                + param.substring(param.length() - 4, param.length());
    }

    /**
     * <p>获取本机公网ip.</p>
     *
     * @return String
     */
    public static String getLocalPublicIp() {
        String html = HttpUtil.httpGet(HttpUtil.SELECT_PUBLIC_IP_ADDRESS);
        return html.substring(html.indexOf("[") + 1, html.indexOf("]"));
    }

    /**
     * <p>getLocalOS.</p>
     *
     * @return String
     * 获取本机操作系统名称
     */
    public static String getLocalOS() {
        return SystemUtils.OS_NAME;
    }

    /**
     * <p>获取当前系统中 JVM最大可用堆空间.</p>
     *
     * @return Long
     */
    public static Long getJVMUsableMemory() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return max - total + free;
    }

    /**
     * <p>替换表情符号.</p>
     *
     * @param str String
     * @return String
     */
    public static String replaceEmoji(String str) {
        return str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
    }

    /**
     * <p>手机号码前三后四脱敏</p>
     *
     * @param mobile String
     * @return String
     */
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != ELEVEN)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * <p>身份证前三后四脱敏</p>
     *
     * @param id String
     * @return String
     */
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < EIGHT)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * <p>护照前2后3位脱敏，护照一般为8或9位</p>
     *
     * @param id String
     * @return String
     */
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < EIGHT)) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5])
                .replace("\0", "*").contains(id.substring(id.length() - 3));
    }
}
