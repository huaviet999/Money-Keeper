package com.example.local.database.dao;

import com.example.local.model.TransactionModel;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public abstract class TransactionDao {

    @Insert
    public abstract void insert(TransactionModel transactionModel);

    @Query("SELECT * FROM transaction_table")
    public abstract List<TransactionModel> getAllTransactionData();

    @Query("SELECT * FROM transaction_table WHERE transaction_id=:transactionId")
    public abstract TransactionModel getTransactionById(int transactionId);

    @Query("DELETE FROM transaction_table")
    public abstract void deleteAllTransactionData();

    @Query("DELETE FROM transaction_table WHERE transaction_id=:transactionId")
    public abstract void deleteTransactionById(int transactionId);
}
