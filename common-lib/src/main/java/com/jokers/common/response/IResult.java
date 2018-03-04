package com.jokers.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;

/**
 * <p>IResult interface.</p>
 *
 * @author yuton
 * @version $Id: $Id
 */
public interface IResult<T> extends Serializable {

    /**
     * <p>setResult.</p>
     *
     * @param result a T object.
     */
    void setResult(T result);

    /**
     * <p>toJson.</p>
     *
     * @return a {@link java.lang.String} object.
     * @throws com.fasterxml.jackson.core.JsonProcessingException if any.
     */
    String toJson() throws JsonProcessingException;

    /**
     * <p>isSuccessful.</p>
     *
     * @return a boolean.
     */
    boolean isSuccessful();

    /**
     * <p>setCode.</p>
     *
     * @param code a {@link java.lang.Integer} object.
     */
    void setCode(Integer code);

    /**
     * <p>setMsg.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    void setMsg(String msg);

    /**
     * <p>getCode.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getCode();

    /**
     * <p>getMsg.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMsg();

    /**
     * <p>getResult.</p>
     *
     * @return a T object.
     */
    T getResult();
}
