package com.example.local.database.dao;

import com.example.local.model.CategoryModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class CategoryDao {
    @Insert
    public abstract void insert(CategoryModel categoryModel);

    @Query("SELECT * FROM category_table")
    public abstract List<CategoryModel> getAllCategories();

    @Query("SELECT * FROM category_table WHERE type=:type")
    public abstract List<CategoryModel> getCategoriesByType(String type);

    @Query("SELECT * FROM category_table WHERE name=:categoryName")
    public abstract CategoryModel getCategoryByName(String categoryName);
}
