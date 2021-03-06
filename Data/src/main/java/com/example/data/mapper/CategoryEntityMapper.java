package com.example.data.mapper;

import com.example.data.entity.CategoryEntity;
import com.example.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 3/19/2020
 */
public class CategoryEntityMapper implements BaseMapper<CategoryEntity, Category> {
    @Override
    public CategoryEntity mapToEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(category.getCategoryId());
        categoryEntity.setName(category.getName());
        categoryEntity.setType(category.getType());
        categoryEntity.setNImage(category.getNImage());
        categoryEntity.setCImage(category.getCImage());
        categoryEntity.setColorId(category.getColorId());
        return categoryEntity;
    }

    @Override
    public Category mapFromEntity(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryEntity.getCategoryId());
        category.setName(categoryEntity.getName());
        category.setType(categoryEntity.getType());
        category.setCImage(categoryEntity.getCImage());
        category.setNImage(categoryEntity.getNImage());
        category.setColorId(categoryEntity.getColorId());
        return category;
    }

    public List<Category> mapFromEntities(List<CategoryEntity> categoryEntities) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryList.add(mapFromEntity(categoryEntity));
        }
        return categoryList;
    }
}
