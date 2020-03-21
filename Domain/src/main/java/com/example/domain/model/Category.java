package com.example.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int categoryId;
    private String name;
    private String type;
    private String nImage;
    private String cImage;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNImage() {
        return nImage;
    }

    public void setNImage(String nImage) {
        this.nImage = nImage;
    }

    public String getCImage() {
        return cImage;
    }

    public void setCImage(String cImage) {
        this.cImage = cImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
