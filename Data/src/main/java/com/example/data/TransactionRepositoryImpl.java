package com.example.data;

import com.example.data.repository.TransactionDataLocal;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    TransactionDataLocal transactionDataLocal;

    @Inject
    public TransactionRepositoryImpl(TransactionDataLocal transactionDataLocal) {
        this.transactionDataLocal = transactionDataLocal;

    }

    @Override
    public Completable saveTransaction(String type, String categoryName, long amount, long date, String memo) {
        return transactionDataLocal.saveTransaction(type, categoryName, amount, date, memo);
    }
}
