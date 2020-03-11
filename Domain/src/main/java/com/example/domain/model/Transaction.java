package com.example.domain.model;

public class Transaction {
    private String expenseType;
    private String expenseNumber;
    private String accountName;
    private String transactionDate;

    public Transaction(String expenseType, String expenseNumber, String accountName, String transactionDate) {
        this.expenseType = expenseType;
        this.expenseNumber = expenseNumber;
        this.accountName = accountName;
        this.transactionDate = transactionDate;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseNumber() {
        return expenseNumber;
    }

    public void setExpenseNumber(String expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
