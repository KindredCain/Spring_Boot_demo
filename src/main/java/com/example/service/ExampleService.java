package com.example.service;

import com.example.enums.ResultEnum;
import com.example.exception.ExampleException;
import com.example.model.Example;
import com.example.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NWJ on 2017/6/18.
 */

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    public void add(Example example) {
        exampleRepository.save(example);
    }

    public void update(Example example) throws Exception {
        Example exampleItem = this.findOne(example.getId());
        if (exampleItem == null) {
            throw new ExampleException(ResultEnum.ERROR_101);
        } else {
            exampleItem.setName(example.getName());
            exampleItem.setPwd(example.getPwd());
            exampleRepository.save(exampleItem);
        }
    }

    public void delete(Example example) {
        exampleRepository.delete(example.getId());
    }

    public List<Example> findAll() {
        return exampleRepository.findAll();
    }

    public List<Example> findByName(String name) {
        return exampleRepository.findByName(name);
    }

    public Example findOne(String id) {
        return exampleRepository.findOne(id);
    }

    public List<Example> findByPwdIs() {
        return exampleRepository.findByPwdIs();
    }

    public List<Example> findByPwdName(String pwd) {
        return exampleRepository.findByPwdName(pwd);
    }

    public List<Example> findByNamePwd(String name) {
        return exampleRepository.findByNamePwd(name);
    }

    /*事务处理*/
    @Transactional
    public void addTwo(Example e1, Example e2) {
        exampleRepository.save(e1);
        exampleRepository.save(e2);
    }
}
