package com.cookinghub.recipes.model.recipes;

public class GenericUnit extends Unit {

    private double value;

    public GenericUnit(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Unit copy() {
        return new GenericUnit(value);
    }
}
