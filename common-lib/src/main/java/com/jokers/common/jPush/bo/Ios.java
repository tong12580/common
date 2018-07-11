package com.jokers.common.jPush.bo;

import com.google.gson.JsonObject;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:28 2017/11/10
 */
public class Ios {


    /**
     * alert : Hi, JPush!
     * sound : default
     * badge : +1
     */

    private String alert;
    private String sound;
    private String badge;
    private JsonObject extras;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public JsonObject getExtras() {
        return extras;
    }

    public void setExtras(JsonObject extras) {
        this.extras = extras;
    }
}
