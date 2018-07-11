package com.jokers.common.jPush.bo;

import lombok.Data;

/**
 * @author yuton
 * @version 1.0
 * @since 上午11:10 2017/11/10
 */
@Data
public class PushMessage {
    private String token;
    private String title;
    private String content;
    private Integer userId;
    private String sound;
    private Integer type;
}
