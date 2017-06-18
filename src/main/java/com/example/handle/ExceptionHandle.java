package com.example.handle;

import com.example.enums.ResultEnum;
import com.example.exception.ExampleException;
import com.example.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NWJ on 2017/6/18.
 */

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ExampleException) {
            ExampleException exampleException = (ExampleException) e;
            return Result.error(exampleException.getCode(), exampleException.getMessage());
        } else {
            System.out.println(e);
            return Result.error(ResultEnum.ERROR_UNKONW.getCode(), ResultEnum.ERROR_UNKONW.getMsg());
        }
    }
}
