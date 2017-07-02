package com.example.controller;

import com.example.model.Example;
import com.example.service.ExampleService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by NWJ on 2017/6/18.
 */

@RestController
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @PostMapping(value = "/modify")
    public Result modify(Example example) throws Exception {
        exampleService.update(example);
        return Result.success();
    }

    @GetMapping(value = "/findall")
    public Result findAll() {
        return Result.success(exampleService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public Result find(@PathVariable("id") String id) {
        return Result.success(exampleService.findOne(id));
    }

    @PostMapping(value = "/findbyname")
    public Result findByName(@RequestParam("name") String name) {
        return Result.success(exampleService.findByName(name));
    }

    @PostMapping(value = "/delete")
    public Result delete(Example example) {
        exampleService.delete(example);
        return Result.success();
    }

    @PostMapping(value = "/findbypwdis")
    public Result findByPwdIs() {
        return Result.success(exampleService.findByPwdIs());
    }

    @PostMapping(value = "/findbypwdname")
    public Result findByPwdName(Example example) {
        return Result.success(exampleService.findByPwdName(example.getPwd()));
    }

    @PostMapping(value = "/findbynamepwd")
    public Result findByNamePwd(Example example) {
        return Result.success(exampleService.findByNamePwd(example.getName()));
    }

    @PostMapping(value = "/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }
}
