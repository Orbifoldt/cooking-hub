package com.cookinghub.recipes.model.recipes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VolumeTest {

    private static final double delta = 1e-6;

    @Test
    public void volumeTest(){
        double milliliter = 1.2345;
        Volume volume = new Volume(milliliter);

        assertEquals(milliliter, volume.getValue(), delta);
        assertEquals(Unit.UnitType.VOLUME, volume.getUnitType());
    }

    @Test
    public void conversionsTest(){
        double ml = 354.882355;
        double liters = 0.354882355;
        double cups = 1.5;
        double tsps = 72.;
        double tbsp = 24.;

        Volume volume = Volume.fromCups(cups);

        assertEquals("Value should display milliliters", ml, volume.getValue(), delta);
        assertEquals("Conversion to mL failed:", ml, volume.getMilliliter(), delta);
        assertEquals("Conversion to liter failed:", liters, volume.getLiter(), delta);
        assertEquals("Conversion to cups failed:", cups, volume.getCup(), delta);
        assertEquals("Conversion to tsp failed:", tsps, volume.getTeaspoon(), delta);
        assertEquals("Conversion to tbsp failed:", tbsp, volume.getTablespoon(), delta);
    }

    @Test
    public void copyVolumeTest(){
        double milliliter = 1842.76;
        Volume volume = new Volume(milliliter);
        Volume copy = volume.copy();

        assertEquals(milliliter, copy.getMilliliter(), delta);
        assertEquals(Unit.UnitType.VOLUME, copy.getUnitType());
    }


    @Test
    public void toStringTest(){
        double milliliter = 75;
        Volume volume = new Volume(milliliter);
        assertEquals("75ml",volume.toString());
    }

    @Test
    public void toStringDecimalsTest(){
        double milliliter = 879.1000;
        Volume volume = new Volume(milliliter);
        assertEquals("879.1ml",volume.toString());
    }

    @Test
    public void toStringDecimalsFloorTest(){
        double milliliter = 1.24;
        Volume volume = new Volume(milliliter);
        assertEquals("1.2ml",volume.toString());
    }

    @Test
    public void toStringDecimalsCeilTest(){
        double milliliter = 1.26;
        Volume volume = new Volume(milliliter);
        assertEquals("1.3ml",volume.toString());
    }
    
}
