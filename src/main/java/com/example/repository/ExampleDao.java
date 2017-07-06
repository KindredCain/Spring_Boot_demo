package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by NWJ on 2017/7/6.
 * 自己编辑Dao实现类
 */

@Repository
public class ExampleDao {

    @Autowired
    private EntityManagerFactory emf;

    public List<Object[]> findSame(){
        EntityManager em = emf.createEntityManager();
        /*重复列名一定要添加别名，不然报错*/
        String sql = "SELECT a.id AS a, b.id AS b FROM example a, example b WHERE a.pwd = b.pwd";
        Query query =  em.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();
        return result;
    }
}
