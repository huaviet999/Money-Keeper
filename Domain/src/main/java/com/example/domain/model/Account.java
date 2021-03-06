package com.example.domain.model;

public class Account {
    private long income;
    private long expense;
    private long total;

    public Account(long income, long expense) {
        this.income = income;
        this.expense = expense;
        this.total = this.income - this.expense;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getExpense() {
        return expense;
    }

    public void setExpense(long expense) {
        this.expense = expense;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
