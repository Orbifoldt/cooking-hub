package com.cookinghub.recipes.model.recipes;

/**
 * Contains static factory methods for generating specific {@link Unit} implementations
 */
public final class Units {

    public static Unit produceUnit(Unit.UnitType unitType, double amount){
        switch (unitType){
            case MASS:
                return new Mass(amount);
            case VOLUME:
                return new Volume(amount);
            default:
                return new GenericUnit(amount);
        }
    }
}
