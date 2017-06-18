package com.example.exception;

import com.example.enums.ResultEnum;

/**
 * Created by NWJ on 2017/6/18.
 */

public class ExampleException extends RuntimeException {

    private int code;

    public ExampleException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
