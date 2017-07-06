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

    @PostMapping(value = "/findsame")
    public Result findSame() {
        return Result.success(exampleService.findSame());
    }

    @PostMapping(value = "/findsamebydao")
    public Result findSameByDao() {
        return Result.success(exampleService.findSameByDao());
    }

    @PostMapping(value = "/findsamepwd")
    public Result findSamePwd(Example example) {
        return Result.success(exampleService.findSamePwd(example.getPwd()));
    }

    @PostMapping(value = "/search")
    public Result search(Example example) {
        return Result.success(exampleService.search(example));
    }

    @PostMapping(value = "/examplepage")
    public Result examplePage(@RequestParam String pwd,
                              @RequestParam(defaultValue = "1") String page,
                              @RequestParam(defaultValue = "3") String pageSize) {
        return Result.success(exampleService.examplePage(pwd, Integer.valueOf(page) - 1, Integer.valueOf(pageSize)));
    }

    @PostMapping(value = "/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }
}
