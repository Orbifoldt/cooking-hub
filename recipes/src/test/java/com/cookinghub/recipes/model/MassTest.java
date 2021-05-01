package com.cookinghub.recipes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class MassTest {

    private static final double delta = 1e-6;

    @Test
    public void massTest(){
        double gram = 1.2345;
        Mass mass = new Mass(gram);

        assertEquals(gram, mass.getValue(), delta);
        assertEquals(Unit.UnitType.MASS, mass.getUnitType());
    }

    @Test
    public void conversionsTest(){
        double gram = 30.;
        double kilogram = 0.03;
        double ounce = 1.05821886;
        double pound = 0.0661386787;

        Mass ounceMass = Mass.fromOunces(ounce);

        assertEquals("Value should display gram", gram, ounceMass.getValue(), delta);
        assertEquals("Conversion to gram failed:", gram, ounceMass.getGram(), delta);
        assertEquals("Conversion to kilogram failed:", kilogram, ounceMass.getKilogram(), delta);
        assertEquals("Conversion to pound failed:", pound, ounceMass.getPounds(), delta);
        assertEquals("Conversion to ounces failed:", ounce, ounceMass.getOunces(), delta);
    }

    @Test
    public void copyMassTest(){
        double gram = 879.543;
        Mass mass = new Mass(gram);
        Mass copy = mass.copy();

        assertEquals(gram, copy.getGram(), delta);
        assertEquals(Unit.UnitType.MASS, copy.getUnitType());
    }

    @Test
    public void toStringTest(){
        double gram = 75;
        Mass mass = new Mass(gram);
        assertEquals("75g",mass.toString());
    }

    @Test
    public void toStringDecimalsTest(){
        double gram = 879.1000;
        Mass mass = new Mass(gram);
        assertEquals("879.1g",mass.toString());
    }

    @Test
    public void toStringDecimalsFloorTest(){
        double gram = 1.24;
        Mass mass = new Mass(gram);
        assertEquals("1.2g",mass.toString());
    }

    @Test
    public void toStringDecimalsCeilTest(){
        double gram = 1.26;
        Mass mass = new Mass(gram);
        assertEquals("1.3g",mass.toString());
    }
}
