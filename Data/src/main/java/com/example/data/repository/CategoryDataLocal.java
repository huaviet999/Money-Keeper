package com.example.data.repository;

import com.example.data.entity.CategoryEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface CategoryDataLocal {
    Maybe<List<CategoryEntity>> getAllCategories();
}
