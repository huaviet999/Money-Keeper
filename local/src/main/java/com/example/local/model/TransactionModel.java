package com.example.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class TransactionModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    private int transactionId;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "memo")
    private String memo;
    @ColumnInfo(name = "amount")
    private long amount;
    @ColumnInfo(name = "date")
    private long date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
