package com.example.domain.model;

/**
 * Created by Viet Hua on 3/15/2020
 */
public enum ExpenseType {
    Others(0),
    Food(1),
    Transport(2),
    Shopping(3),
    Bills(4),
    Health(5),
    Telephones(6),
    Home(7),
    Education(8),
    Travel(9),
    Insurance(10),
    Social(11),
    Gift(12),
    Sport(13);

    private final int value;

    ExpenseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
