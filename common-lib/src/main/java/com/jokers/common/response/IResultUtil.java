package com.jokers.common.response;

import com.jokers.common.message.ResultMessage;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>IResultUtil class.</p>
 *
 * @author yutong
 * @version 1.0
 * 返回结果统计调用方法
 * @since 2018/2/19 14:10
 */
public class IResultUtil {

    /**
     * 原始提示
     *
     * @param code   int
     * @param msg    String
     * @param result T
     * @param <T>    <T>
     * @return IResult<T>
     */
    public static <T> IResult<T> result(int code, String msg, T result) {
        IResult<T> rs = new Result<>();
        rs.setCode(code);
        rs.setMsg(msg);
        rs.setResult(result);
        return rs;
    }

    /**
     * 成功提示
     *
     * @return IResult
     */
    public static IResult<String> successResult() {
        return new Result<>(ResultMessage.STATUS_SUCCESS);
    }

    /**
     * 成功提示 无返回参数
     *
     * @param resultMessage ResultMessage
     * @return IResult
     */
    public static IResult<String> successResult(ResultMessage resultMessage) {
        return new Result<>(resultMessage);
    }


    /**
     * 成功提示 有返回
     *
     * @param resultMessage ResultMessage
     * @param resultMessage ResultMessage
     * @param result        T
     * @param <T>           T
     * @return IResult
     */
    public static <T> IResult<T> successResult(ResultMessage resultMessage, T result) {
        return new Result<>(resultMessage, result);
    }

    /**
     * 成功提示 有返回
     *
     * @param result T
     * @param <T>    T
     * @return IResult
     */
    public static <T> IResult<T> successResult(T result) {
        return new Result<>(ResultMessage.STATUS_SUCCESS, result);
    }

    /**
     * 错误提示
     *
     * @return IResult
     */
    public static IResult<String> errorResult() {
        return new Result<>(ResultMessage.STATUS_FAILURE);
    }

    /**
     * 错误提示
     *
     * @param msg 提示信息
     * @return IResult
     */
    public static IResult<String> errorResult(String msg) {
        IResult<String> result = new Result<>();
        result.setCode(ResultMessage.ERROR_PROMPT.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 错误提示 无返回
     *
     * @param resultMessage ResultMessage
     * @param <T>           a T object.
     * @return IResult
     */
    public static <T> IResult<T> errorResult(ResultMessage resultMessage) {
        return new Result<>(resultMessage);
    }

    /**
     * 错误提示
     *
     * @param code {@link java.lang.Integer}
     * @param msg  String
     * @return IResult
     */
    public static <T> IResult<T> errorResult(int code, String msg) {
        IResult<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 错误提示
     *
     * @param resultMessage ResultMessage
     * @param specificMsg   a {@link java.lang.String} object.
     * @return IResult
     */
    public static <T> IResult<T> errorResult(ResultMessage resultMessage, String specificMsg) {
        IResult<T> result = new Result<>();
        specificMsg = StringUtils.isBlank(specificMsg) ? "" : specificMsg;
        result.setCode(resultMessage.getCode());
        result.setMsg(resultMessage.getMsg().replace("{}", specificMsg));
        return result;
    }
}

