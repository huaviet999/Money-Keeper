package com.example.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryId;
    private String name;
    private String nImage;
    private String cImage;

    public Category(String name, String nImage, String cImage) {
        this.name = name;
        this.nImage = nImage;
        this.cImage = cImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getnImage() {
        return nImage;
    }

    public void setnImage(String nImage) {
        this.nImage = nImage;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public static List<Category> getDefaultCategoriesList() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Food", "nfood","cfood"));
        categoryList.add(new Category("Transport", "ntransport","ctransport"));
        categoryList.add(new Category("Shopping", "nshopping","cshopping"));
        categoryList.add(new Category("Bills", "nbill","cbill"));
        categoryList.add(new Category("Health", "nhealth","chealth"));
        categoryList.add(new Category("Telephone", "nphone","cphone"));
        categoryList.add(new Category("Home", "nhome","chome"));
        categoryList.add(new Category("Education", "neducation","ceducation"));
        categoryList.add(new Category("Travel", "ntravel","ctravel"));
        categoryList.add(new Category("Insurance", "ninsurance","cinsurance"));
        categoryList.add(new Category("Social", "nsocial","csocial"));
        categoryList.add(new Category("Sport", "nsport","csport"));
        categoryList.add(new Category("Gift", "ngift","cgift"));
        categoryList.add(new Category("Others", "nother","cother"));
        return categoryList;
    }
}
