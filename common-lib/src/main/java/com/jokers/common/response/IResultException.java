package com.jokers.common.response;

import com.jokers.common.message.ResultMessage;

/**
 * @author yuton
 * @version 1.0
 *  返回信息异常处理
 * @since 2016/11/30 11:42
 */
public class IResultException extends RuntimeException {

    public IResultException() {
        super();
    }

    public IResultException(ResultMessage message) {
        super(message.getMsg());
    }

    public IResultException(String message) {
        super(message);
    }

    public IResultException(ResultMessage message, String uiMessage) {
        super(message.getMsg().replace("{}", uiMessage));
    }

    public IResultException(ResultMessage message, Throwable cause) {
        super(message.getMsg(), cause);
    }

    public IResultException(Throwable cause) {
        super(cause);
    }

    protected IResultException(ResultMessage message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message.getMsg(), cause, enableSuppression, writableStackTrace);
    }

}
