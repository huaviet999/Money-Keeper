package com.example.domain.model;

public enum IncomeType {
    Others(0),
    Salary(1),
    Awards(2),
    Gift(3),
    Investment(4),
    Rental(5),
    Refunds(6);

    private int value;
    IncomeType(int value){
        this.value = value;
    }

}
