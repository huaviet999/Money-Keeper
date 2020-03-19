package com.example.domain.repository;

import com.example.domain.model.Transaction;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * Created by Viet Hua on 3/18/2020
 */
public interface TransactionRepository {
    Completable saveTransaction(String type,String categoryName,long amount,long date,String memo);
    Maybe<List<Transaction>> getAllTransactionData();
    Maybe<Transaction> getTransactionById(int transactionId);
}
