package com.jokers.common.validator;


import com.jokers.common.CommonTools;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * IsMobileValidator
 * <p>是否是手机号</p>
 * Created by yuTong on 20181104.
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return CommonTools.isPhone(s);
        }
        return true;
    }
}
