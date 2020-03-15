package com.example.domain.model;

public class ModelTest1 {
    private String percent;
    private String money;
    private ExpenseType expenseType;

    public ModelTest1(String percent, String money, ExpenseType expenseType) {
        this.percent = percent;
        this.money = money;
        this.expenseType = expenseType;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
