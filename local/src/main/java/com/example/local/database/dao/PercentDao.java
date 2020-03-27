package com.example.local.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class PercentDao {
    @Query("SELECT COALESCE(sum(COALESCE(amount,0)), 0) FROM transaction_table WHERE category_name=:category")
    public abstract long getSumAmountByCategory(String category);

    @Query("SELECT COALESCE(sum(COALESCE(amount,0)), 0) FROM transaction_table WHERE type=:transactionType")
    public abstract long getSumAmount(String transactionType);
}
