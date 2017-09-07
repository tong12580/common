package com.jokers.pojo.bo;

import lombok.Data;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/7/10 19:48
 */
@Data
public class MailAuthBo {
    private String email;
    private String authCode;
    private String subject;
    private String msg;
}
