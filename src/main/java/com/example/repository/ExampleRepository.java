package com.example.repository;

import com.example.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by NWJ on 2017/6/18.
 */
public interface ExampleRepository extends JpaRepository<Example, String> {
    public List<Example> findByName(String name);
}
