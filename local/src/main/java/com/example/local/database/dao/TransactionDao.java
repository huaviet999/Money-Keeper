package com.example.local.database.dao;

import com.example.local.model.TransactionModel;



import androidx.room.Dao;
import androidx.room.Insert;



@Dao
public abstract class TransactionDao {

    @Insert
    public abstract void insert(TransactionModel transactionModel);

}
