package com.jokers.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;

public interface IResult<T> extends Serializable {

    void setResult(T result);

    String toJson() throws JsonProcessingException;

    boolean isSuccessful();

    void setCode(Integer code);

    void setMsg(String msg);

    Integer getCode();

    String getMsg();

    T getResult();
}