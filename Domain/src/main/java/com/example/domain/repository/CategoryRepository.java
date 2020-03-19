package com.example.domain.repository;

import com.example.domain.model.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface CategoryRepository {
    Maybe<List<Category>> getAllCategories();
}
