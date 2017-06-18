package com.example.controller;

import com.example.enums.ResultEnum;
import com.example.exception.ExampleException;
import com.example.model.Example;
import com.example.service.ExampleService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by NWJ on 2017/6/18.
 */

@RestController
public class GuestController {
    @Autowired
    private ExampleService exampleService;

    @PostMapping(value = "/add")
    public Result add(Example example) {
        exampleService.add(example);
        return Result.success();
    }

    @PostMapping(value = "/login")
    public Result login(Example example, HttpSession session) throws Exception {
        Example exampleItem = exampleService.findByName(example.getName()).get(0);
        if (exampleItem.getPwd().equals(example.getPwd())) {
            session.setAttribute("ID", exampleItem.getId());
            return Result.success();
        } else {
            throw new ExampleException(ResultEnum.ERROR_102);
        }
    }
}
