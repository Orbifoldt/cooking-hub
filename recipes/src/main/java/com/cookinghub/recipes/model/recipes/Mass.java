package com.cookinghub.recipes.model.recipes;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * A {@link Unit} for holding mass values
 */
public class Mass extends Unit {

    public static final double KILOGRAM = 1000.;
    public static final double MILLIGRAM = 1/1000.;

    public static final double POUND = 453.59237;
    public static final double OUNCE = POUND / 16;
    public static final double STONE = 14 * POUND;

    private double gram = 0.;
    private final UnitType unitType = UnitType.MASS;

    /**
     * @param gram The amount of grammes
     */
    public Mass(double gram){
        this.gram = gram;
    }

    /**
     * Create a Mass object from a given amount of ounces
     * @param ounces
     * @return
     */
    public static Mass fromOunces(double ounces){
        return new Mass(ounces * OUNCE);
    }

    /**
     * @return The mass in grammes
     */
    @Override
    public double getValue() {
        return getGram();
    }

    /**
     * @return The mass converted to grammes
     */
    public double getGram(){
        return gram;
    }

    /**
     * @return The mass converted to kilogrammes
     */
    public double getKilogram(){
        return getGram() / KILOGRAM;
    }

    /**
     * @return The mass converted to ounces
     */
    public double getOunces(){
        return getGram() / OUNCE;
    }

    /**
     * @return The mass converted to pounds
     */
    public double getPounds(){
        return getGram() / POUND;
    }

    /**
     * Create a copy of this without any dependencies on this.
     * @return A copy of this
     */
    @Override
    public Mass copy() {
        return new Mass(gram);
    }

    /**
     * @return {@link Unit.UnitType#MASS}
     */
    @Override
    public UnitType getUnitType(){
        return unitType;
    }

    /**
     * Get a string with the amount of gram of this up to one decimal using Locale.US format. If the decimal value ends
     * in a .0 this will be omitted. So e.g. if getValue() returns 15.0000, this method returns "15g", and  for 25.5 this
     * returns "25.5g".
     * @return
     */
    @Override
    public String toString(){
        Locale.setDefault(Locale.US);
        DecimalFormat df = new DecimalFormat("0.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(gram) + "g";
    }
    
}
