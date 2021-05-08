package com.cookinghub.recipes.model.recipes;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class Volume extends Unit {

    public static final double LITER = 1000.;
    public static final double DECILITER = 100.;
    public static final double CENTILITER = 10.;

    // Value of volume units converted to milliliters
    public static final double CUP = 236.588237;
    public static final double TSP = 4.92892159;
    public static final double TBSP = 14.7867648;
    public static final double FLOZ = 29.5735296;
    public static final double GAL = 3785.41178;
    public static final double PINT = 473.176473;
    public static final double QUART = 946.352946;
    public static final double PINT_UK = 568.261485 ;
    public static final double QUART_UK = 1136.52297;

    private final double milliliter;
    private final UnitType unitType = UnitType.VOLUME;

    /**
     * @param milliliter The amount of milliliters
     */
    public Volume(double milliliter){
        this.milliliter = milliliter;
    }

    /**
     * Create a Volume object from an amount in cups
     * @param cups
     * @return
     */
    public static Volume fromCups(double cups){
        return new Volume(cups * CUP);
    }

    /**
     * @return The volume in milliliters
     */
    @Override
    public double getValue() {
        return getMilliliter();
    }

    /**
     * @return The volume converted to milliliters
     */
    public double getMilliliter() {
        return milliliter;
    }

    /**
     * @return The volume converted to liters
     */
    public double getLiter() {
        return getMilliliter() / LITER;
    }

    /**
     * @return The volume converted to cups
     */
    public double getCup() {
        return getMilliliter() / CUP;
    }

    /**
     * @return The volume converted to teaspoons
     */
    public double getTeaspoon() {
        return getMilliliter() / TSP;
    }


    /**
     * @return The volume converted to tablespoons
     */
    public double getTablespoon() {
        return getMilliliter() / TBSP;
    }

    /**
     * Create a copy of this without any dependencies on this.
     * @return A copy of this
     */
    @Override
    public Volume copy() {
        return new Volume(milliliter);
    }

    /**
     * @return {@link Unit.UnitType#VOLUME}
     */
    @Override
    public UnitType getUnitType(){
        return unitType;
    }

    /**
     * Get a string with the amount of milliliters of this up to one decimal using Locale.US format. If the decimal
     * value ends in a .0 this will be omitted. So e.g. if getValue() returns 15.0000, this method returns "15ml", and
     * for 25.5 this returns "25.5ml".
     * @return
     */
    @Override
    public String toString(){
        Locale.setDefault(Locale.US);
        DecimalFormat df = new DecimalFormat("0.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(milliliter) + "ml";
    }

}
