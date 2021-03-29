package com.example.demo.models.bindingModels;

import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddingBindingModel {
    @NotNull
    private TypeOfMadeEnum type;
    @NotEmpty
    @Size(min=4)
    private String name;
    @Positive
    @NotNull
    private int metal;
    @Positive
    @NotNull
    private int gas;
    @Positive
    @NotNull
    private int diamond;
    @Positive
    @NotNull
    private int energy;
    @Positive
    @NotNull
    private int time;
    @NotEmpty
    @Length(min=6)
    private String description;

    public TypeOfMadeEnum getType() {
        return type;
    }

    public AddingBindingModel setType(TypeOfMadeEnum type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddingBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getMetal() {
        return metal;
    }

    public AddingBindingModel setMetal(int metal) {
        this.metal = metal;
        return this;
    }

    public int getGas() {
        return gas;
    }

    public AddingBindingModel setGas(int gas) {
        this.gas = gas;
        return this;
    }

    public int getDiamond() {
        return diamond;
    }

    public AddingBindingModel setDiamond(int diamond) {
        this.diamond = diamond;
        return this;
    }

    public int getEnergy() {
        return energy;
    }

    public AddingBindingModel setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public int getTime() {
        return time;
    }

    public AddingBindingModel setTime(int time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddingBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "AddingBindingModel{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", metal=" + metal +
                ", gas=" + gas +
                ", diamond=" + diamond +
                ", energy=" + energy +
                ", time=" + time +
                ", description='" + description + '\'' +
                '}';
    }
}
