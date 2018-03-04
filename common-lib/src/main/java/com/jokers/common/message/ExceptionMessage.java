package com.jokers.common.message;

import lombok.Getter;

/**
 * <p>ExceptionMessage class.</p>
 *
 * @author yuton
 * @since 2016/8/8 16:25
 * @version 1.0
 */
public enum ExceptionMessage {

    SPLIT_PARAMETERS_EXCEPTION(1, "拆分参数异常: {}"),
    IO_EXCEPTION(2, "IO异常: {}"),
    MALFORMED_URL_EXCEPTION(3, "获取URL异常: {}"),
    TO_BIOMEDICAL_EXCEPTION(4, "数值转换异常: {}"),
    CONNECT_TIME_OUT(5,"连接超时: {}"),
    NUMBER_FORMAT_EXCEPTION(6,"格式化数据异常: {}");

    @Getter
    Integer exceptionCode;
    @Getter
    String exceptionMsg;

    ExceptionMessage(int exceptionCode, String exceptionMsg) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }
}
