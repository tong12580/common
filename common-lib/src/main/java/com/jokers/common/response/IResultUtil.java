package com.jokers.common.response;

import com.jokers.common.message.ResultMessage;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yutong
 * @version 1.0
 * @description 返回结果统计调用方法
 * @since 2018/2/19 14:10
 */
public class IResultUtil {
    /**
     * 成功提示
     *
     * @return {@link IResult}
     */
    public static IResult<String> successResult() {
        return new Result<>(ResultMessage.STATUS_SUCCESS);
    }

    /**
     * 成功提示 无返回参数
     *
     * @param resultMessage {@link ResultMessage}
     * @return {@link IResult}
     */
    public static IResult<String> successResult(ResultMessage resultMessage) {
        return new Result<>(resultMessage);
    }


    /**
     * 成功提示 有返回
     *
     * @param resultMessage {@link ResultMessage}
     * @param result        {@link T}
     * @param <T>           {@link T}
     * @return {@link IResult}
     */
    public static <T> IResult<T> successResult(ResultMessage resultMessage, T result) {
        return new Result<>(resultMessage, result);
    }

    /**
     * 成功提示 有返回
     *
     * @param result {@link T}
     * @param <T>    {@link T}
     * @return {@link IResult}
     */
    public static <T> IResult<T> successResult(T result) {
        return new Result<>(ResultMessage.STATUS_SUCCESS, result);
    }

    /**
     * 错误提示
     *
     * @return {@link IResult}
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
        result.setMsg(ResultMessage.ERROR_PROMPT.getMsg().replace("{}", msg));
        return result;
    }

    /**
     * 错误提示 无返回
     *
     * @param resultMessage {@link ResultMessage}
     * @return {@link IResult}
     */
    public static <T> IResult<T> errorResult(ResultMessage resultMessage) {
        return new Result<>(resultMessage);
    }

    /**
     * 错误提示
     *
     * @param code {@link Integer}
     * @param msg  {@link String}
     * @return {@link IResult}
     */
    public static IResult<String> errorResult(int code, String msg) {
        IResult<String> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 错误提示
     *
     * @param resultMessage {@link ResultMessage}
     * @return {@link IResult}
     */
    public static IResult<String> errorResult(ResultMessage resultMessage, String specificMsg) {
        IResult<String> result = new Result<>();
        specificMsg = StringUtils.isBlank(specificMsg) ? "" : specificMsg;
        result.setCode(resultMessage.getCode());
        result.setMsg(resultMessage.getMsg().replace("{}", specificMsg));
        return result;
    }
}

