package com.example.data.entity;

public class CategoryEntity {
    private int categoryId;
    private String name;
    private String nImage;
    private String cImage;
    private String type;

    public CategoryEntity() {

    }

    public CategoryEntity(String name, String nImage, String cImage, String type) {
        this.name = name;
        this.nImage = nImage;
        this.cImage = cImage;
        this.type = type;
    }

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
