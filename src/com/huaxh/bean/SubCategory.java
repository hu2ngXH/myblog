package com.huaxh.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubCategory {
    private int id;
    private String name;
    private int mCategoryId;
    private Number count;

    public SubCategory() {

    }

    public SubCategory(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.name = (String) map.get("name");
        this.mCategoryId = (int) map.get("mCategoryId");
        this.count = (Number) map.get("count");
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mCategoryId=" + mCategoryId +
                ", count=" + count +
                '}';
    }

    public List toList() {
        List list = new ArrayList();
        list.add(this.name);
        list.add(this.mCategoryId);
        return list;
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

    public int getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }
}
