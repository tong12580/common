package com.jokers.common.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuton
 * @version 1.0
 * @description 时间工具类
 * @since 2017/2/22 20:10
 */
@Slf4j
public class DateUtil {
    private static final String PATTERN_HAVE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter PATTERN_TIME_STAMP = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * @param date 格式为：yyyy-MM-dd HH:mm:ss {@link String}
     * @return boolean
     * @description 判断是否为正确日期
     */
    public static boolean isDate(String date) {
        FastDateFormat format = FastDateFormat.getInstance(PATTERN_HAVE_TIME);
        try {
            return format.format(format.parse(date)).equals(date);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * @param date 时间类型
     * @return yyyy-MM-dd {@link String}
     * @description 对时间格式进行格式化
     */
    public static String format(Date date) {
        return DateFormatUtils.format(date, DateFormatUtils.ISO_DATE_FORMAT.getPattern());
    }

    /***
     * 格式化时间参数
     * @param date 时间参数
     * @return HH:mm:ss {@link String}
     */
    public static String formatTime(Date date) {
        return DateFormatUtils.format(date, DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
    }

    /***
     * 格式化时间
     * @param date    时间参数
     * @param pattern 格式化参数类型
     * @return {@link String}
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }


    /**
     * @return {@link String}
     * @description 当前时间  yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDatetime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_HAVE_TIME));
    }

    /**
     * @param pattern {@link String}
     * @return {@link String}
     * @description 当前时间 pattern
     */
    public static String getCurDatetime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param pattern String
     * @param zoneId  ZoneId
     * @return String
     * @description 获取某时区当前时间的格式化
     */
    public static String getCurDatetime(String pattern, ZoneId zoneId) {
        return LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern(pattern));
    }

    /***
     * 格式化时间
     * @param date yyyy-MM-dd
     * @return {@link Date}
     */
    public static Date getCurDate(String date, String pattern) {
        FastDateFormat format = FastDateFormat.getInstance(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * @param date1 等待比较第一个时间
     * @param date2 等待比较第二个时间
     * @return 比较结果
     * @description 判断两个时间是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * @param cal1 比较第一个日历类
     * @param cal2 比较第二个日历类
     * @return 比较结果
     * @description 比较两个日历类数据是否相同
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return DateUtils.isSameDay(cal1, cal2);
    }

    /**
     * @param date 需要新增时间
     * @param year 增加年份
     * @return 增加后年份
     * @description 增减n年份
     */
    public static Date addYears(Date date, int year) {
        return DateUtils.addYears(date, year);
    }

    /**
     * @param date  传入时间
     * @param month 需要增加月份
     * @return 增加月份
     * @description 增加月份
     */
    public static Date addMonths(Date date, int month) {
        return DateUtils.addMonths(date, month);
    }

    /**
     * @param date   当前时间
     * @param amount 需要增加周
     * @return 增加后时间
     * @description 增加周
     */
    public static Date addWeeks(Date date, int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    /**
     * @param date   当前时间
     * @param amount 需要增加天数
     * @return 增加后时间
     * @description 增加天
     */
    public static Date addDays(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * @param date   当前时间
     * @param amount 增加小时数
     * @return 增加后时间
     * @description 增加小时
     */
    public static Date addHours(Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    /**
     * @param date   当前时间
     * @param amount 增加分钟数
     * @return 增加后时间
     * @description 增加分钟
     */
    public static Date addMinutes(Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    /**
     * @param date   当前时间
     * @param amount 增加秒数
     * @return 增加后时间
     * @description 增加秒
     */
    public static Date addSeconds(Date date, int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    /**
     * @param date   当前时间
     * @param amount 增加毫秒
     * @return 增加后时间
     * @description 添加毫秒
     */
    public static Date addMilliseconds(Date date, int amount) {
        return DateUtils.addMilliseconds(date, amount);
    }

    /**
     * @param date1 {@link Date}
     * @param date2 {@link Date}
     * @return {@link Integer}
     * @description 计算两个时间相差的天数 先格式化时间
     */
    public static int daysBetween(Date date1, Date date2, String pattern) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        date1 = getCurDate(format(date1, pattern), pattern);
        date2 = getCurDate(format(date2, pattern), pattern);
        Calendar cal = Calendar.getInstance();
        assert date1 != null;
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        assert date2 != null;
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /***
     * 判断是否是周末
     * @param date {@link Date}
     * @return boolean
     */
    public static boolean isWeekEnd(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /***
     * 当前日期前n个交易日
     * @param date {@link Date}
     * @param n int
     * @return Date
     */
    public Date tradingTomorrowDay(Date date, int n) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        return new Date(calendar.getTimeInMillis());
    }

    /***
     * 计算两个时间相差的天数
     * @param date1 {@link Date}
     * @param date2 {@link Date}
     * @return int
     */
    public static int daysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        Long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return between_days.intValue();
    }

    /**
     * @param time1 {@link Date}
     * @param time2 {@link Date}
     * @return int
     * @description : 获取1-2的时间差，单位为ms
     */
    public static int getTimestampDeff(Date time1, Date time2) {
        if (time1 == null || time2 == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(time1);
        long m1 = cal.getTimeInMillis();
        cal.setTime(time2);
        long m2 = cal.getTimeInMillis();
        long between_sec = (m2 - m1);
        return Integer.parseInt(String.valueOf(between_sec));
    }

    /**
     * 获取时间戳
     *
     * @return long
     */
    public static long getTimestamp() {
        return Instant.now().getEpochSecond();
    }

    /**
     * @param birthday Date
     * @return String
     * @description : 计算年龄
     */
    public static String getAgeByBirthday(Date birthday) {
        int days = daysBetween(birthday, new Date());
        int year = days / 365;
        int month = (days % 365) / 30;
        String age = "";
        if (year > 0) {
            age = year + "岁";
            if (month > 0) {
                age = age + month + "个月";
            }
        } else if (month > 0) {
            age = month + "个月";
        }
        return age;
    }

    /**
     * 获取当前UTC 格式 PATTERN_TIME_STAMP
     *
     * @return String
     */
    public static String getNowUTCTimeStamp() {
        return OffsetDateTime.now().format(PATTERN_TIME_STAMP);
    }
}
