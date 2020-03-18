package com.example.data.repository;

import io.reactivex.rxjava3.core.Completable;

public interface TransactionDataLocal {
    Completable saveTransaction(String type,String categoryName,long amount,long date,String memo);

}
