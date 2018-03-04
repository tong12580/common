package com.jokers.pojo.enums;

import lombok.Getter;

/**
 * Created by yuton on 2016/9/3.
 *
 * @author yuton
 * @version $Id: $Id
 */
public enum ContextParamDictionary {
    PROJECT_PATH("webAppRootKey", "projectRootPath");
    @Getter
    private String paramName;
    @Getter
    private String paramValue;

    ContextParamDictionary(String name, String value) {
        paramName = name;
        paramValue = value;
    }
}
