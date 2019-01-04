package com.example.core.model;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class BaseController {

    /**
     * 接口输入参数合法性校验
     *
     * @param result
     */
    protected void validate(BindingResult result){
        if(result.hasFieldErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            errorList.stream().forEach(item -> Assert.isTrue(item.isBindingFailure(),item.getDefaultMessage()));
        }
    }
}
