package com.example.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "n_image")
    private String nImage;
    @ColumnInfo(name = "c_image")
    private String cImage;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "color_id")
    private String colorId;

    public CategoryModel() {

    }

    @Ignore
    public CategoryModel(String name, String nImage, String cImage, String type, String colorId) {
        this.name = name;
        this.nImage = nImage;
        this.cImage = cImage;
        this.type = type;
        this.colorId = colorId;
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

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }
}
