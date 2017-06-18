package com.example.aspect;

import com.example.enums.ResultEnum;
import com.example.exception.ExampleException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by NWJ on 2017/6/18.
 */

@Aspect
@Component
public class ExampleAspect {

    @Pointcut("execution(public * com.example.controller.ExampleController.*(..))")
    public void point() {
    }

    @Before("point()")
    public void doBefore() throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = (String) request.getSession().getAttribute("ID");
        if (id == null) {
            throw new ExampleException(ResultEnum.ERROR_100);
        }
    }
}
