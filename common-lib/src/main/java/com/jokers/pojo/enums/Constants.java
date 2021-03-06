package com.jokers.pojo.enums;

import lombok.Getter;

/**
 * <p>Constants class.</p>
 *
 * @author yutong
 *  常量接口
 * @since 2016-3-8 下午06:36:58
 * @version $Id: $Id
 */
public enum Constants {

    /**
     *  session key 常量定义
     */
    SESSION_KEY_USER("sessionUser"), // 用户session

    /**
     *  图片保存路径
     */
    FILE_BASE_PATH("file_base_path"),
    /**
     *  图片保存路径
     */
    STATIC_BASE_PATH("static_base_path"),

    /**
     *  文件映射基路径名称
     */
    UPLOAD_BASE_FOLDER("upload"),

    /**
     *  用户缓存信息key
     */
    USER_KEY("user_key"),

    /**
     *  注册手机验证码key
     */
    CODE_COUNT_NUM("CODE_COUNT_NUM_"),

    /**
     *  固定图片压缩尺寸常量
     */
    SIZE_HEAD_IMG_W(200),
    SIZE_HEAD_IMG_H(150),;

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
