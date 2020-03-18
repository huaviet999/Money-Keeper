package com.example.data.repository;

import com.example.data.entity.TransactionEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public interface TransactionDataLocal {
    Completable saveTransaction(String type,String categoryName,long amount,long date,String memo);
    Maybe<List<TransactionEntity>> getAllTransactionData();
}
