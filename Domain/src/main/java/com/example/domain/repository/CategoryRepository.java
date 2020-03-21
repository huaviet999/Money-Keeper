package com.example.domain.repository;

import com.example.domain.model.Category;
import com.example.domain.model.Transaction;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface CategoryRepository {
    Maybe<List<Category>> getAllCategories();
    Maybe<List<Category>> getCategoriesByType(String type);
    Maybe<List<Transaction>> getCategoriesByName(List<Transaction> transaction);
    Maybe<Transaction> getCategoryByName(Transaction transaction);
}
