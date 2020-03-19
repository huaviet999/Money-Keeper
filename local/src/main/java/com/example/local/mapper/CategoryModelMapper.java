package com.example.local.mapper;

import com.example.data.entity.CategoryEntity;
import com.example.local.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryModelMapper implements BaseMapper<CategoryEntity, CategoryModel> {
    @Override
    public CategoryModel mapToModel(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public CategoryEntity mapFromModel(CategoryModel categoryModel) {
        if (categoryModel == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryModel.getCategoryId());
        categoryEntity.setName(categoryModel.getName());
        categoryEntity.setCImage(categoryModel.getCImage());
        categoryEntity.setNImage(categoryModel.getNImage());
        return categoryEntity;
    }

    public List<CategoryEntity> mapFromModels(List<CategoryModel> categoryModels) {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categoryEntityList.add(mapFromModel(categoryModel));
        }
        return categoryEntityList;
    }
}
