package com.example.data.repository;

import com.example.data.entity.TransactionEntity;
import com.example.domain.model.Transaction;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public interface TransactionDataLocal {
    Completable saveTransaction(String type,String categoryName,long amount,long date,String memo);
    Completable deleteAllTransactionsData();
    Completable deleteTransactionById(int transactionId);
    Maybe<List<TransactionEntity>> getAllTransactionData();
    Maybe<List<TransactionEntity>> getTransactionByType(String transactionType);
    Maybe<List<TransactionEntity>> getTransactionListByCategory(String categoryName);
    Maybe<TransactionEntity> getTransactionById(int transactionId);
}
