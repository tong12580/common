package com.jokers.common.jPush.bo;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:18 2017/11/10
 */
public class SmsMessage {

    /**
     * content : sms msg content
     * delay_time : 3600
     */

    private String content;
    private Integer delay_time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDelay_time() {
        return delay_time;
    }

    public void setDelay_time(Integer delay_time) {
        this.delay_time = delay_time;
    }
}
