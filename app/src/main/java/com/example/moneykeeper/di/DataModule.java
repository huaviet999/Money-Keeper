package com.example.moneykeeper.di;

import com.example.data.CategoryRepositoryImpl;
import com.example.data.PercentRepositoryImpl;
import com.example.data.TransactionRepositoryImpl;
import com.example.domain.repository.CategoryRepository;
import com.example.domain.repository.PercentRepository;
import com.example.domain.repository.TransactionRepository;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 3/18/2020
 */
@Module
public abstract class DataModule {
    @Binds
    public abstract TransactionRepository bindTransactionRepository(TransactionRepositoryImpl impl);

    @Binds
    public abstract CategoryRepository bindCategoryRepository(CategoryRepositoryImpl impl);

    @Binds
    public  abstract PercentRepository bindPercentRepository(PercentRepositoryImpl impl);
}
