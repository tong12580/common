package com.jokers.common.jPush.bo;

import java.util.Set;

/**
 * @author yuton
 * @version 1.0
 * @since 上午10:36 2017/11/10
 */
public class Audience {
    private Set<String> registration_id;

    public Set<String> getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(Set<String> registration_id) {
        this.registration_id = registration_id;
    }
}
