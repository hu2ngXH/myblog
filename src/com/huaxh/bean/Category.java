package com.huaxh.bean;

import java.util.Map;

public class Category {
    private int mCategoryId;
    private String mCategoryName;
    private int sCategoryId;
    private String sCategoryName;
    private Number sub_count;

    public Category() {
    }

    public Category(int mCategoryId, String mCategoryName, int sCategoryId, String sCategoryName, Number sub_count) {
        this.mCategoryId = mCategoryId;
        this.mCategoryName = mCategoryName;
        this.sCategoryId = sCategoryId;
        this.sCategoryName = sCategoryName;
        this.sub_count = sub_count;
    }

    public Category(Map<String, Object> map) {
        this.mCategoryId = (int) map.get("id");
        this.mCategoryName = (String) map.get("name");
        this.sCategoryId = (int) map.get("sCategoryId");
        this.sCategoryName = (String) map.get("sCategoryName");
        this.sub_count = (Number) map.get("count");
    }

    public int getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public int getsCategoryId() {
        return sCategoryId;
    }

    public void setsCategoryId(int sCategoryId) {
        this.sCategoryId = sCategoryId;
    }

    public String getsCategoryName() {
        return sCategoryName;
    }

    public void setsCategoryName(String sCategoryName) {
        this.sCategoryName = sCategoryName;
    }

    public Number getSub_count() {
        return sub_count;
    }

    public void setSub_count(Number sub_count) {
        this.sub_count = sub_count;
    }
}
