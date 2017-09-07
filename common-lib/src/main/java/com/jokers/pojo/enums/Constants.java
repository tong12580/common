package com.jokers.pojo.enums;

import lombok.Getter;

/**
 * @author yutong
 * @description 常量接口
 * @since 2016-3-8 下午06:36:58
 */
public enum Constants {

    /**
     * @description 站点入口
     */
    BASE_PATH("https://so360.org/"),

    /**
     * @description 网站域名
     */
    DOMAIN("so360.org"),

    /**
     * @description session key 常量定义
     */
    SESSION_KEY_USER("sessionUser"), // 用户session

    /**
     * @description 图片保存路径
     */
    FILE_BASE_PATH("file_base_path"),
    /**
     * @description 图片保存路径
     */
    STATIC_BASE_PATH("static_base_path"),

    /**
     * @description 文件映射基路径名称
     */
    UPLOAD_BASE_FOLDER("upload"),

    /**
     * @description 用户缓存信息key
     */
    USER_KEY("user_key"),

    /**
     * @description 注册手机验证码key
     */
    CODE_COUNT_NUM("CODE_COUNT_NUM_"),

    /**
     * @description 固定图片压缩尺寸常量
     */
    SIZE_HEAD_IMG_W(200),
    SIZE_HEAD_IMG_H(150),
    WATCH_KEEP("i:k"),
    WATCH_REDIS_ADDRESS("i:adr"),
    WATCH_REDIS_ADDRESS_QUEUE("i:Q:"),
    SECURITY_REDIS_ADDRESS(":adr"),
    SECURITY_REDIS_ADDRESS_QUEUE(":Q:"),
    SECURITY_KEEP(":k"),
    SMART_STICK_REDIS_ADDRESS("hot:"),
    MOBILE("mobile");

    @Getter
    private String constants;
    @Getter
    private Integer constant;

    Constants(String constants) {
        this.constants = constants;
    }

    Constants(Integer constant) {
        this.constant = constant;
    }

}
