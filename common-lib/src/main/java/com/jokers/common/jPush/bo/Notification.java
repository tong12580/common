package com.jokers.common.jPush.bo;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:26 2017/11/10
 */
public class Notification {
    private Android android;
    private Ios ios;

    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }

    public Ios getIos() {
        return ios;
    }

    public void setIos(Ios ios) {
        this.ios = ios;
    }
}
