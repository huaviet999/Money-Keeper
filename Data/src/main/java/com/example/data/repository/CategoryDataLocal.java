package com.example.data.repository;

import com.example.data.entity.CategoryEntity;
import com.example.data.entity.TransactionEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface CategoryDataLocal {
    Maybe<List<CategoryEntity>> getAllCategories();
    Maybe<List<CategoryEntity>> getCategoriesByType(String type);
    Maybe<List<TransactionEntity>> getCategoriesByName(List<TransactionEntity> transactionEntity);
    Maybe<TransactionEntity> getCategoryByName(TransactionEntity transactionEntity);
}
