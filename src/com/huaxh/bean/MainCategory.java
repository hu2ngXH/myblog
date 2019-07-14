package com.huaxh.bean;

import java.util.List;
import java.util.Map;

public class MainCategory {
    private int id;
    private String name;
    private Number count;
    private List<SubCategory> sublist;

    public MainCategory() {

    }

    //创建一个分类不一定要指定其子分类
    public MainCategory(int id, String name, Number count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    //创建一个分类不一定要指定其子分类
    public MainCategory(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.name = (String) map.get("name");
        this.count = (Number) map.get("count");
    }

    @Override
    public String toString() {
        return "MainCategory{id=" + this.id +
                ", name=" + this.name +
                ", count=" + this.count +
                ", sublist=" + this.sublist + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public List<SubCategory> getSublist() {
        return sublist;
    }

    public void setSublist(List<SubCategory> sublist) {
        this.sublist = sublist;
    }
}
