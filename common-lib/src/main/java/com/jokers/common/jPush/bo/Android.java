package com.jokers.common.jPush.bo;

import com.google.gson.JsonObject;

/**
 * @author yuton
 * @version 1.0
 * @since 2018/7/11 16:27
 */
public class Android {
    private String alert;
    private String title;
    private int builder_id;
    private JsonObject extras;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBuilder_id() {
        return builder_id;
    }

    public void setBuilder_id(int builder_id) {
        this.builder_id = builder_id;
    }

    public JsonObject getExtras() {
        return extras;
    }

    public void setExtras(JsonObject extras) {
        this.extras = extras;
    }
}
