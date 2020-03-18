package com.example.moneykeeper.di;

import com.example.data.repository.TransactionDataLocal;
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
}
