package com.jokers.common.jPush.bo;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:17 2017/11/10
 */
public class Options {

    /**
     * time_to_live : 60
     * apns_production : false
     * apns_collapse_id : jiguang_test_201706011100
     */

    private int time_to_live;
    private boolean apns_production;
    private String apns_collapse_id;

    public int getTime_to_live() {
        return time_to_live;
    }

    public void setTime_to_live(int time_to_live) {
        this.time_to_live = time_to_live;
    }

    public boolean isApns_production() {
        return apns_production;
    }

    public void setApns_production(boolean apns_production) {
        this.apns_production = apns_production;
    }

    public String getApns_collapse_id() {
        return apns_collapse_id;
    }

    public void setApns_collapse_id(String apns_collapse_id) {
        this.apns_collapse_id = apns_collapse_id;
    }
}
