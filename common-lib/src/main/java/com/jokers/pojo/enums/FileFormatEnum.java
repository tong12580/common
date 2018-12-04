package com.jokers.pojo.enums;

import lombok.Getter;

/**
 * FileFormatEnum
 * <p>文件格式枚举</p>
 * Created by yuTong on 20181022.
 */
public enum FileFormatEnum {

    CSV(".csv"),
    EXCEL(".xls"),
    TEXT(".text");

    @Getter
    private String name;

    FileFormatEnum(String name) {
        this.name = name;
    }
}
