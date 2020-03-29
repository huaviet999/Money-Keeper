package com.example.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {

    private int categoryId;
    private String name;
    private String type;
    private String nImage;
    private String cImage;
    private String colorId;

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

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(type, category.type) &&
                Objects.equals(nImage, category.nImage) &&
                Objects.equals(cImage, category.cImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, nImage, cImage);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", nImage='" + nImage + '\'' +
                ", cImage='" + cImage + '\'' +
                ", colorId='" + colorId + '\'' +
                '}';
    }
}
