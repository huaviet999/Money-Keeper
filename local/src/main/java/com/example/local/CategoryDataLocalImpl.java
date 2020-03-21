package com.example.local;

import com.example.data.entity.CategoryEntity;
import com.example.data.entity.TransactionEntity;
import com.example.data.mapper.TransactionEntityMapper;
import com.example.data.repository.CategoryDataLocal;
import com.example.domain.executor.ExecutionThread;
import com.example.local.database.MoneyKeeperDatabase;
import com.example.local.database.dao.CategoryDao;
import com.example.local.mapper.CategoryModelMapper;
import com.example.local.mapper.TransactionModelMapper;
import com.example.local.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;

/**
 * Created by Viet Hua on 3/19/2020
 */
public class CategoryDataLocalImpl implements CategoryDataLocal {
    private ExecutionThread executionThread;
    private CategoryDao categoryDao;
    private CategoryModelMapper categoryModelMapper;

    @Inject
    public CategoryDataLocalImpl(MoneyKeeperDatabase moneyKeeperDatabase, ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.categoryDao = moneyKeeperDatabase.categoryDao();
        categoryModelMapper = new CategoryModelMapper();

    }

    @Override
    public Maybe<List<CategoryEntity>> getAllCategories() {
        return Maybe.create(new MaybeOnSubscribe<List<CategoryEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<CategoryEntity>> emitter) throws Throwable {
                List<CategoryModel> categoryModelList = categoryDao.getAllCategories();
                emitter.onSuccess(categoryModelMapper.mapFromModels(categoryModelList));
            }
        }).subscribeOn(executionThread.io());
    }

    @Override
    public Maybe<List<CategoryEntity>> getCategoriesByType(final String type) {
        return Maybe.create(new MaybeOnSubscribe<List<CategoryEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<CategoryEntity>> emitter) throws Throwable {
                List<CategoryModel> categoryModelList = categoryDao.getCategoriesByType(type);
                emitter.onSuccess(categoryModelMapper.mapFromModels(categoryModelList));
            }
        });
    }

    @Override
    public Maybe<List<TransactionEntity>> getCategoriesByName(final List<TransactionEntity> transactionEntities) {
        return Maybe.create(new MaybeOnSubscribe<List<TransactionEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<TransactionEntity>> emitter) throws Throwable {
                List<TransactionEntity> result = new ArrayList<>();
                for (TransactionEntity transactionEntity : transactionEntities) {
                    CategoryModel categoryModel = categoryDao.getCategoryByName(transactionEntity.getCategoryEntity().getName());
                    transactionEntity.setCategoryEntity(categoryModelMapper.mapFromModel(categoryModel));
                    result.add(transactionEntity);
                }
                emitter.onSuccess(result);

            }
        });
    }

    @Override
    public Maybe<TransactionEntity> getCategoryByName(final TransactionEntity transactionEntity) {
        return Maybe.create(new MaybeOnSubscribe<TransactionEntity>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<TransactionEntity> emitter) throws Throwable {
                CategoryModel categoryModel = categoryDao.getCategoryByName(transactionEntity.getCategoryEntity().getName());
                transactionEntity.setCategoryEntity(categoryModelMapper.mapFromModel(categoryModel));
                emitter.onSuccess(transactionEntity);
            }
        });
    }
}
