package com.example.domain.repository;

import com.example.domain.model.Transaction;

import io.reactivex.rxjava3.core.Completable;

/**
 * Created by Viet Hua on 3/18/2020
 */
public interface TransactionRepository {
    Completable saveTransaction(String type,String categoryName,long amount,long date,String memo);
}
