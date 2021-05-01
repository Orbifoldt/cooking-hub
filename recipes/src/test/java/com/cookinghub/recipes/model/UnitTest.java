package com.cookinghub.recipes.model;

import static com.cookinghub.recipes.model.Unit.extractUnit;
import static com.cookinghub.recipes.model.Unit.parseValue;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class UnitTest {

    private final double delta = 1e-6;

    @Test
    public void parseValueSimpleIntTest() {
        String testString = "5";
        assertEquals(5., parseValue(testString), delta);
    }

    @Test
    public void parseValueSimpleDoubleTest() {
        String testString = "3.9";
        assertEquals(3.9, parseValue(testString), delta);
    }


    @Test
    public void parseValueTextBeforeAndAfterTest() {
        String testString = "bla bla 105.93 bla and some more bla";
        assertEquals(105.93, parseValue(testString), delta);
    }

    @Test
    public void parseValueMultipleNumbersTest() {
        String testString = "10 5.9 18.5468 17.456";
        assertEquals(17.456, parseValue(testString), delta);
    }

    @Test
    public void parseValueCommasTest() {
        String testString = "1,500,987.07";
        assertEquals(1500987.07, parseValue(testString), delta);
    }


    @Test
    public void extractUnitSimpleTest() {
        String testString = "100g";
        assertEquals("g", extractUnit(testString));
    }

    @Test
    public void extractUnitWhiteSpaceTest() {
        String testString = "  100  g    ";
        assertEquals("g", extractUnit(testString));
    }

    @Test
    public void extractUnitPeriodsTest() {
        String testString = "1.234.567gram";
        assertEquals("gram", extractUnit(testString));
    }

    @Test
    public void extractUnitPeriodBeforeUnitTest() {
        String testString = "400.kilo";
        assertEquals("kilo", extractUnit(testString));
    }

    @Test
    public void extractUnitPeriodAfterUnitTest() {
        String testString = "300lbs.";
        assertEquals("lbs", extractUnit(testString));
    }

    @Test
    public void extractUnitCommasTest() {
        String testString = "98,756l";
        assertEquals("l", extractUnit(testString));
    }

    @Test
    public void extractUnitCommasAndPeriodsTest() {
        String testString = "98,756,123.025tsp";
        assertEquals("tsp", extractUnit(testString));
    }

    @Test
    public void extractUnitWordBeforeTest() {
        String testString = "sugar: 5 tbsp";
        assertEquals("tbsp", extractUnit(testString));
    }

    @Test
    public void extractUnitWordsAfterTest() {
        String testString = "5 cups sugar";
        assertEquals("cups", extractUnit(testString));
    }

    @Test
    public void extractUnitWordsAndNumbersBeforeTest() {
        String testString = "2x400ml";
        assertEquals("ml", extractUnit(testString));
    }

    @Test
    public void extractUnitNoUnitTest() {
        String testString = "  5,000.00 ";
        assertThrows(IllegalArgumentException.class, ()-> extractUnit(testString));
    }

    @Test
    public void extractUnitNoNumbersTest() {
        String testString = ".,. gram";
        assertThrows(IllegalArgumentException.class, ()-> extractUnit(testString));
    }

    @Test
    public void extractUnitNoLettersAfterNumberTest() {
        String testString = "gram 1,000.";
        assertThrows(IllegalArgumentException.class, ()-> extractUnit(testString));
    }

    @Test
    public void extractUnitInvalidFormatTest() {
        String testString = "1000.. gram";
        assertThrows(IllegalArgumentException.class, ()-> extractUnit(testString));
    }
}