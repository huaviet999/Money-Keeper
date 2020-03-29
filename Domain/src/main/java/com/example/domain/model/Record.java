package com.example.domain.model;


public class Record {
    private long income;
    private long expense;
    private long total;
    private int day;
    private int month;
    private int year;

    public Record(long income, long expense, int day, int month, int year) {
        this.income = income;
        this.expense = expense;
        this.day = day;
        this.month = month;
        this.year = year;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Record{" +
                "income=" + income +
                ", expense=" + expense +
                ", total=" + total +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
