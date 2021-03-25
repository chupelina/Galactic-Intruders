package com.example.demo.models.bindingModels;

import javax.validation.constraints.Positive;

public class BankBindingModel {
    @Positive
    private int count;
    private String type;
    private String wanted;

    public int getCount() {
        return count;
    }

    public BankBindingModel setCount(int count) {
        this.count = count;
        return this;
    }

    public String getType() {
        return type;
    }

    public BankBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getWanted() {
        return wanted;
    }

    public BankBindingModel setWanted(String wanted) {
        this.wanted = wanted;
        return this;
    }

    @Override
    public String toString() {
        return "BankBindingModel{" +
                "count=" + count +
                ", type='" + type + '\'' +
                ", wanted='" + wanted + '\'' +
                '}';
    }
}
