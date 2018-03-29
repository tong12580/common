package com.jokers.common.response;

import com.jokers.common.json.JsonUtil;
import com.jokers.common.message.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * <p>Result class.</p>
 *
 * @author yuton
 * @version $Id: $Id
 */
public class Result<T> implements IResult<T> {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Integer code = 0;
    private String msg;
    private T result;

    /**
     * <p>Constructor for Result.</p>
     */
    public Result() {
    }

    /**
     * <p>Constructor for Result.</p>
     *
     * @param resultMessage a {@link com.jokers.common.message.ResultMessage} object.
     */
    public Result(ResultMessage resultMessage) {
        code = resultMessage.getCode();
        msg = resultMessage.getMsg();
    }

    /**
     * <p>Constructor for Result.</p>
     *
     * @param resultMessage a {@link com.jokers.common.message.ResultMessage} object.
     * @param result a T object.
     */
    public Result(ResultMessage resultMessage, T result) {
        code = resultMessage.getCode();
        msg = resultMessage.getMsg();
        this.result = result;
    }

    /** {@inheritDoc} */
    @Override
    public void setResult(T result) {
        this.result = result;
    }

    /** {@inheritDoc} */
    @Override
    public String toJson() throws JsonProcessingException {
        String result = JsonUtil.toJson(this);
        log.info(result);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSuccessful() {
        return code.equals(200);
    }

    /** {@inheritDoc} */
    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    /** {@inheritDoc} */
    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getCode() {
        return code;
    }

    /** {@inheritDoc} */
    @Override
    public String getMsg() {
        return msg;
    }

    /** {@inheritDoc} */
    @Override
    public T getResult() {
        return result;
    }
}
