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
        return null;
    }

    @Override
    public Category mapFromEntity(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryEntity.getCategoryId());
        category.setName(categoryEntity.getName());
        category.setCImage(categoryEntity.getCImage());
        category.setNImage(categoryEntity.getNImage());
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
