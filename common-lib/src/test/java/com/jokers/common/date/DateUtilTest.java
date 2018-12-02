package com.jokers.common.date;

import org.junit.jupiter.api.Test;

/**
 * <p>DateUtilTest</p>
 * <span></span>
 *
 * @author yuTong
 * @version 1.0
 * @since 2018/12/2 20:48
 */
class DateUtilTest {

    @Test
    void isDate() {
        System.out.println(DateUtil.isDate("2018-11-15", "yyyy-MM-dd"));
    }
}