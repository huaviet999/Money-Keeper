package com.example.domain.model;

public class ModelTest1 {
    private String type;
    private String percent;
    private String money;

    public ModelTest1(String type, String percent, String money) {
        this.type = type;
        this.percent = percent;
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
