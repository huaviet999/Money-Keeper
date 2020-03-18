package com.example.moneykeeper.di;

import com.example.data.TransactionRepositoryImpl;
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
}
