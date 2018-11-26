package com.jokers.common.validator;


import com.jokers.common.CommonTools;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * IsNumOrCharValidator
 * <p>只有数字或字母</p>
 * Created by yuTong on 20181115.
 */
public class IsNumOrCharValidator implements ConstraintValidator<IsNumOrChar, String> {
    private boolean required = false;

    @Override
    public void initialize(IsNumOrChar constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return CommonTools.isNumOrChar(value);
        }
        return true;
    }
}
