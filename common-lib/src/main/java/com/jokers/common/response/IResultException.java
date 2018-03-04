package com.jokers.common.response;

import com.jokers.common.message.ResultMessage;

/**
 * <p>IResultException class.</p>
 *
 * @author yuton
 * @version 1.0
 *  返回信息异常处理
 * @since 2016/11/30 11:42
 */
public class IResultException extends RuntimeException {

    /**
     * <p>Constructor for IResultException.</p>
     */
    public IResultException() {
        super();
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param message a {@link com.jokers.common.message.ResultMessage} object.
     */
    public IResultException(ResultMessage message) {
        super(message.getMsg());
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public IResultException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param message a {@link com.jokers.common.message.ResultMessage} object.
     * @param uiMessage a {@link java.lang.String} object.
     */
    public IResultException(ResultMessage message, String uiMessage) {
        super(message.getMsg().replace("{}", uiMessage));
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param message a {@link com.jokers.common.message.ResultMessage} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public IResultException(ResultMessage message, Throwable cause) {
        super(message.getMsg(), cause);
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public IResultException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>Constructor for IResultException.</p>
     *
     * @param message a {@link com.jokers.common.message.ResultMessage} object.
     * @param cause a {@link java.lang.Throwable} object.
     * @param enableSuppression a boolean.
     * @param writableStackTrace a boolean.
     */
    protected IResultException(ResultMessage message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message.getMsg(), cause, enableSuppression, writableStackTrace);
    }

}
