package com.example.model;

/**
 * Created by NWJ on 2017/7/6.
 * 自定义类存储查询
 */
public class IndexObject {

    private String exampleNameA;
    private String exampleNameB;

    public String getExampleNameA() {
        return exampleNameA;
    }

    public void setExampleNameA(String exampleNameA) {
        this.exampleNameA = exampleNameA;
    }

    public String getExampleNameB() {
        return exampleNameB;
    }

    public void setExampleNameB(String exampleNameB) {
        this.exampleNameB = exampleNameB;
    }

    public IndexObject(String exampleNameA, String exampleNameB) {
        this.exampleNameA = exampleNameA;
        this.exampleNameB = exampleNameB;
    }
}
