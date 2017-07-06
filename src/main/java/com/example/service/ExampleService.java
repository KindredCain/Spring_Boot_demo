package com.example.service;

import com.example.enums.ResultEnum;
import com.example.exception.ExampleException;
import com.example.model.Example;
import com.example.model.IndexObject;
import com.example.repository.ExampleDao;
import com.example.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NWJ on 2017/6/18.
 */

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private ExampleDao exampleDao;

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

    /*List<Object[]>存储多表查询结果*/
    public List<Object[]> findSame() {
        return exampleRepository.findSame();
    }

    /*自己编辑Dao实现类*/
    public List<Object[]> findSameByDao() {
        return exampleDao.findSame();
    }

    /*自定义类存储查询*/
    public List<IndexObject> findSamePwd(String pwd) {
        return exampleRepository.findBySamePwd(pwd);
    }

    /*自定义动态查询*/
    public List<Example> search (Example example) {
        return exampleRepository.findAll(new Specification<Example>(){
            @Override
            public Predicate toPredicate(Root<Example> root, CriteriaQuery<?> query, CriteriaBuilder cb){
                List<Predicate> list = new ArrayList<Predicate>();
                if(example != null && !example.getName().equals("") ){
                    list.add(cb.like(root.get("name"), "%" + example.getName() + "%"));
                }

                if(example != null && !example.getPwd().equals("") ){
                    list.add(cb.equal(root.<String> get("pwd"), example.getPwd()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
    }

    /*分页排序查询
    * 可单独使用分页或排序（排序改成List<>）
    * Page<>或许具体数据为"content":*/
    public Page<Example> examplePage (String pwd, Integer page, Integer pageSize){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"createTime"),
                new Sort.Order(Sort.Direction.DESC,"updateTime"));
        return exampleRepository.findByPwd(pwd, new PageRequest(page, pageSize, sort));
    }
}
