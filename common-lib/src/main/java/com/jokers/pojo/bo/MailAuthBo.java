package com.jokers.pojo.bo;

import lombok.Data;

/**
 * <p>MailAuthBo class.</p>
 *
 * @author yuton
 * @version 1.0
 * @since 2017/7/10 19:48
 */
@Data
public class MailAuthBo {
    private String email;
    private String authCode;
    private String subject;
    private String msg;
}
