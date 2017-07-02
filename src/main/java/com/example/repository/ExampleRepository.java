package com.example.repository;

import com.example.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by NWJ on 2017/6/18.
 */
public interface ExampleRepository extends JpaRepository<Example, String> {
    List<Example> findByName(String name);

    /*原生sql*/
    @Query(value = "select * from example a where a.pwd = '123'", nativeQuery = true)
    List<Example> findByPwdIs();

    /*Param注解注入参数*/
    @Query("from Example example where example.name = '123' and example.pwd = :pwd")
    List<Example> findByPwdName(@Param("pwd") String pwd);

    @Query("from Example example where example.pwd = '123' and example.name = ?1")
    List<Example> findByNamePwd(String name);
}
