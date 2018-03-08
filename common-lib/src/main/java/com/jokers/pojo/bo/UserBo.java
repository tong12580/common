package com.jokers.pojo.bo;

/**
 * UserBo
 * Created by yuTong on 2018/03/08.
 */
public class UserBo {
    private String username;
    private Long userId;
    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
