package com.jokers.common.message;

import lombok.Getter;

/**
 * <p>ResultMessage class.</p>
 *
 * @author yuton
 * @version 1.0
 * @since 2016/11/6 16:25
 */
public enum ResultMessage {
    DATABASE_NULL(200, "数据为空: {}"),
    STATUS_SUCCESS(200, "调用成功"),
    STATUS_SUCCESS_ADD(200, "添加成功"),
    STATUS_SUCCESS_UPD(200, "修改成功"),
    STATUS_SUCCESS_DEL(200, "删除成功"),
    STATUS_SUCCESS_GET(200, "查询成功"),
    STATUS_UPDATE_FAILURE(700, "修改{}异常"),
    STATUS_ADD_FAILURE(701, "添加{}异常"),
    STATUS_DEL_FAILURE(702, "删除{}异常"),
    STATUS_UPDATE_EXCEPTION(703, "拒绝修改: {}"),
    STATUS_ADD_LIMIT(704, "{}个数已达上限"),
    STATUS_FAILURE(555, "system exception: {}"),
    INTERNAL_SERVER_ERROR(555, "A system exception: internal service error!"),
    INPUT_PARAMETER_IS_EMPTY(400, "参数{}为空"),
    INPUT_PARAMETER_EXCEPTION(400, "Parameters of the abnormal,{}"),
    ERROR_PROMPT(401, "{}"),
    LOGIN_TIME_OUT(408, "Please login first!"),
    NETWORK_ANOMALY(406, "网络异常"),
    LOGIN_OTHER(410, "Other user login !"),
    ABNORMAL_PERMISSIONS(409, "Abnormal permissions!"),
    REQUEST_PARAMETER_IS_EMPTY(402, "Request parameter is empty!"),
    ID_NUMBER_ILLEGAL(403, "Id number is illegal!"),
    DATABASE_ABNORMAL(501, "Database abnormal: {}"),
    JSON_CONVERSION_TO_BEAN_ABNORMAL(415, "json转换异常: {}");

    @Getter
    private int code;
    @Getter
    private String msg;

    ResultMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
