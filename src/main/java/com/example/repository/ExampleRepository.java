package com.example.repository;

import com.example.model.Example;
import com.example.model.IndexObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by NWJ on 2017/6/18.
 * JpaRepository一般数据库操作接口
 * JpaSpecificationExecutor动态查询接口
 */
public interface ExampleRepository extends JpaRepository<Example, String>, JpaSpecificationExecutor<Example> {
    List<Example> findByName(String name);

    /*原生sql*/
    @Query(value = "select * from example a where a.pwd = '123'", nativeQuery = true)
    List<Example> findByPwdIs();

    /*Param注解注入参数*/
    @Query("from Example example where example.name = '123' and example.pwd = :pwd")
    List<Example> findByPwdName(@Param("pwd") String pwd);

    @Query("from Example example where example.pwd = '123' and example.name = ?1")
    List<Example> findByNamePwd(String name);

    /*List<Object[]>存储多表查询结果*/
    @Query("from Example a, Example b where a.pwd = b.pwd")
    List<Object[]> findSame();

    /*自定义类存储查询*/
    @Query(value = "SELECT new com.example.model.IndexObject(a.name,b.name) " +
            "FROM Example a, Example b " +
            "WHERE a.pwd = b.pwd AND a.pwd = ?1")
    List<IndexObject> findBySamePwd(String pwd);

    /*分页排序查询
    * 排序用 List<> name(?, Sort sort); */
    Page<Example> findByPwd(String pwd, Pageable pageable);
}
