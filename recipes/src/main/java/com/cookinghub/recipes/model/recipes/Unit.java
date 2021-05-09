package com.cookinghub.recipes.model.recipes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A data st
 */
public abstract class Unit implements Copyable<Unit> {

    /**
     * Denotes the type of the unit.
     */
    public enum UnitType {
        /**
         * Denoting mass, used when you measure using e.g. gram, kilogram, ounces or pounds
         */
        MASS,
        /**
         * Denoting volume, used when you measure using e.g. milliliters, liters or cups
         */
        VOLUME,
        /**
         * Denoting any other unit type
         */
        OTHER;
    }

    public UnitType getUnitType(){
        return UnitType.OTHER;
    }

    public abstract double getValue();

    public abstract Unit copy();

    /**
     * @return "unit" if this.getValue() returns 1, or "units" otherwise
     */
    protected String getUnitString(){
        return getValue() == 1. ? "unit" : "units";
    }

    @Override
    public String toString(){
        return "" + getValue() + " " + getUnitString();
    }

    /**
     * Extract the last number found in the provided string. The number may contain comma's or underscores _, and also
     * at most a single decimal point.
     * @param stringValue The string from which the number should be extracted
     * @return A double representing the number contained in the provided argument
     * @throws IllegalArgumentException If no number could be found.
     * @throws NumberFormatException If the number could not be parsed as a double using {@link Double#parseDouble(String)}.
     */
    public static double parseValue(String stringValue) throws IllegalArgumentException, NumberFormatException{
        if(!stringValue.matches(".*\\d.*")){
            throw new IllegalArgumentException("Provided input does not contain any digits.");
        }
        Matcher numberExtraction = Pattern.compile("-?[\\d.,_]+").matcher(stringValue);

        String extractedNumber = null;
        while(numberExtraction.find()){
            extractedNumber = numberExtraction.group();
        }
        if(extractedNumber != null){
            extractedNumber = extractedNumber.replaceAll("[,_]","");
            return Double.parseDouble(extractedNumber);
        } else {
            throw new IllegalArgumentException("No number could be extracted.");
        }
    }

    /**
     * Extract the unit from a string. The unit is the first consecutive string of letters after a number. This number
     * may contain any number of periods and comma's, but may have at most either a single period or comma at its end.
     * Between the number and the unit there may be between 0 up to 32 white space characters. Only A-Z and a-z are
     * considered valid letter characters.
     * @param stringValue The string from which the unit will be extracted
     * @return The last unit found in the argument string, or an empty string if no unit was found
     * @throws IllegalArgumentException if the string does not contain any digits, or if there are no letter characters
     * found after the digits.
     */
    public static String extractUnit(String stringValue) throws IllegalArgumentException{
        if(!stringValue.matches(".*\\d.*")){
            throw new IllegalArgumentException("Provided input does not contain any digits, so no unit can be extracted.");
        } else if(!stringValue.matches(".*\\d+.*[A-Za-z].*")){
            throw new IllegalArgumentException("Provided input does not contain any letter characters after the number.");
        } else if(!stringValue.matches(".*\\d[.,]?\\s{0,32}[A-Za-z].*")){
            throw new IllegalArgumentException("Provided input has an invalid format, no unit found.");
        }
        String lastLettersInStringRegex = "(?<=\\d[.,]?\\s{0,32})[A-Za-z]+(?!\\d+[.,]?\\s*[A-Za-z]+)";
        String unit = "";
        Matcher matcher = Pattern.compile(lastLettersInStringRegex).matcher(stringValue);
        if(matcher.find()){
            unit = matcher.group();
        }
        return unit;
    }

}
