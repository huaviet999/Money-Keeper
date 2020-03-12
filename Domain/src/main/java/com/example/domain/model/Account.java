package com.example.domain.model;

public class Account {
    private float income;
    private float expense;
    private float total;

    public Account(float income, float expense) {
        this.income = income;
        this.expense = expense;
        this.total = income - expense;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
