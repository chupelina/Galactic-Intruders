package com.example.demo.models.bindingModels;

import com.example.demo.models.entities.enums.MaterialEnum;

import javax.validation.constraints.Positive;

public class BankBindingModel {
    @Positive
    private int count;
    private MaterialEnum type;
    private MaterialEnum wanted;

    public int getCount() {
        return count;
    }

    public BankBindingModel setCount(int count) {
        this.count = count;
        return this;
    }

    public MaterialEnum getType() {
        return type;
    }

    public BankBindingModel setType(MaterialEnum type) {
        this.type = type;
        return this;
    }

    public MaterialEnum getWanted() {
        return wanted;
    }

    public BankBindingModel setWanted(MaterialEnum wanted) {
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
