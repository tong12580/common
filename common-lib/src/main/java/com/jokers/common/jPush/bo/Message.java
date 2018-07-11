package com.jokers.common.jPush.bo;

import com.google.gson.JsonObject;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:20 2017/11/10
 */
public class Message {

    /**
     * msg_content : Hi,JPush
     * content_type : text
     * title : msg
     * extras : {"key":"value"}
     */

    private String msg_content;
    private String content_type;
    private String title;
    private JsonObject extras;

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonObject getExtras() {
        return extras;
    }

    public void setExtras(JsonObject extras) {
        this.extras = extras;
    }
}
