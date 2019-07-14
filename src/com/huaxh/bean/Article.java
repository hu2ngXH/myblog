package com.huaxh.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Article {
    private int id;
    private String title;
    private String digest; // 摘要
    private String mdContent; // markdown
    private String htmlContent; // html
    private String createdDate; // todo 这里改成Date是不是要好一点 其实可以当做时间存储 就是存储的时候麻烦了点
    private int mCategoryId; // 主分类id
    private int sCategoryId; // 子分类id
    private String mCategoryName; // 主分类名字
    private String sCategoryName; // 子分类名字
    private int top; //是否置顶 todo 这里应该改为boolean

    public Article() {
    }

    public Article(int id, String title, String digest, String mdContent, String htmlContent, String createdDate,
                   int mCategoryId, int sCategoryId, String mCategoryName, String sCategoryName, int top) {
        this.id = id;
        this.title = title;
        this.digest = digest;
        this.mdContent = mdContent;
        this.htmlContent = htmlContent;
        this.createdDate = createdDate;
        this.mCategoryId = mCategoryId;
        this.sCategoryId = sCategoryId;
        this.mCategoryName = mCategoryName;
        this.sCategoryName = sCategoryName;
        this.top = top;
    }

    public Article(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.title = (String) map.get("title");
        this.digest = (String) map.get("digest");
        this.mdContent = (String) map.get("mdContent");
        this.htmlContent = (String) map.get("htmlContent");
        this.createdDate = (String) map.get("createdDate");
        this.mCategoryId = (int) map.get("mCategoryId");
        this.sCategoryId = (int) map.get("sCategoryId");
        this.mCategoryName = (String) map.get("mCategoryName");
        this.sCategoryName = (String) map.get("sCategoryName");
        this.top = (int) map.get("top");
    }

    @Override
    public String toString() {
        return "Article{id=" + this.id +
                ", title=" + this.title +
                ", digest=" + this.digest +
                ", mdContent=" + this.mdContent +
                ", htmlContent=" + this.htmlContent +
                ", createdDate=" + this.createdDate +
                ", mCategoryId=" + this.mCategoryId +
                ", sCategoryId=" + this.sCategoryId +
                ", mCategoryName=" + this.mCategoryName +
                ", sCategoryName=" + this.sCategoryName +
                ", top=" + this.top + "}";
    }

    public List toList() {
        List list = new ArrayList();//只能存储对象不能存储基本数据类型
        list.add(title);
        list.add(digest);
        list.add(mdContent);
        list.add(htmlContent);
        list.add(createdDate);
        list.add(mCategoryId);
        list.add(sCategoryId);
        list.add(top);
        return list;
    }

    //getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public int getsCategoryId() {
        return sCategoryId;
    }

    public void setsCategoryId(int sCategoryId) {
        this.sCategoryId = sCategoryId;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getsCategoryName() {
        return sCategoryName;
    }

    public void setsCategoryName(String sCategoryName) {
        this.sCategoryName = sCategoryName;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
