package com.example.data;

import com.example.data.entity.CategoryEntity;
import com.example.data.entity.TransactionEntity;
import com.example.data.mapper.CategoryEntityMapper;
import com.example.data.mapper.TransactionEntityMapper;
import com.example.data.repository.CategoryDataLocal;
import com.example.domain.model.Category;
import com.example.domain.model.Transaction;
import com.example.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Function;

public class CategoryRepositoryImpl implements CategoryRepository {

    CategoryDataLocal categoryDataLocal;
    CategoryEntityMapper categoryEntityMapper;
    TransactionEntityMapper transactionEntityMapper;

    @Inject
    public CategoryRepositoryImpl(CategoryDataLocal categoryDataLocal) {
        this.categoryDataLocal = categoryDataLocal;
        transactionEntityMapper = new TransactionEntityMapper();
        categoryEntityMapper = new CategoryEntityMapper();
    }

    @Override
    public Maybe<List<Category>> getAllCategories() {
        return categoryDataLocal.getAllCategories().map(new Function<List<CategoryEntity>, List<Category>>() {
            @Override
            public List<Category> apply(List<CategoryEntity> categoryEntities) throws Throwable {
                return categoryEntityMapper.mapFromEntities(categoryEntities);
            }
        });
    }

    @Override
    public Maybe<List<Category>> getCategoriesByType(String type) {
        return categoryDataLocal.getCategoriesByType(type).map(new Function<List<CategoryEntity>, List<Category>>() {
            @Override
            public List<Category> apply(List<CategoryEntity> categoryEntities) throws Throwable {
                return categoryEntityMapper.mapFromEntities(categoryEntities);
            }
        });
    }

    @Override
    public Maybe<List<Transaction>> getCategoriesByName(List<Transaction> transaction) {
        return categoryDataLocal.getCategoriesByName(transactionEntityMapper.mapToEntities(transaction)).map(new Function<List<TransactionEntity>, List<Transaction>>() {
            @Override
            public List<Transaction> apply(List<TransactionEntity> transactionEntities) throws Throwable {
                return transactionEntityMapper.mapFromEntities(transactionEntities);
            }
        });
    }

    @Override
    public Maybe<Transaction> getCategoryByName(Transaction transaction) {
        return categoryDataLocal.getCategoryByName(transactionEntityMapper.mapToEntity(transaction)).map(new Function<TransactionEntity, Transaction>() {
            @Override
            public Transaction apply(TransactionEntity transactionEntity) throws Throwable {
                return transactionEntityMapper.mapFromEntity(transactionEntity);
            }
        });
    }

}
