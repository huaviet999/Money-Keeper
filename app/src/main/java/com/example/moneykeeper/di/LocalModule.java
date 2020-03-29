package com.example.moneykeeper.di;

import com.example.data.repository.CategoryDataLocal;
import com.example.data.repository.PercentDataLocal;
import com.example.data.repository.TransactionDataLocal;
import com.example.local.CategoryDataLocalImpl;
import com.example.local.PercentDataLocalImpl;
import com.example.local.TransactionDataLocalImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 3/18/2020
 */
@Module
public abstract class LocalModule {
    @Binds
    public abstract TransactionDataLocal bindTransactionDataLocal(TransactionDataLocalImpl impl);

    @Binds
    public abstract CategoryDataLocal bindCategoryDataLocal(CategoryDataLocalImpl impl);

    @Binds
    public abstract PercentDataLocal bindPercentDataLocal(PercentDataLocalImpl impl);
}
