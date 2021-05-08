package com.cookinghub.recipes.model.recipes;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeInstructionTest {

    @Test
    public void recipeInstructionTest(){
        RecipeInstruction recipeInstruction = new RecipeInstruction(15L, 4,"It puts the lotion on its skin");
        assertEquals(15L, recipeInstruction.getRecipeId());
        assertEquals(4, recipeInstruction.getOrdinal());
        assertEquals("It puts the lotion on its skin", recipeInstruction.getInstruction());

        recipeInstruction.setInstruction("Nothing here");
        recipeInstruction.setOrdinal(99);
        assertEquals(15L, recipeInstruction.getRecipeId());
        assertEquals(99, recipeInstruction.getOrdinal());
        assertEquals("Nothing here", recipeInstruction.getInstruction());
    }

    @Test
    public void recipeInstructionComparableTestEqual(){
        RecipeInstruction instruction = new RecipeInstruction(666L, 9,"Freeze!");
        int comp = instruction.compareTo(instruction);
        assertEquals("The instruction should equal itself.", 0, comp);
    }

    @Test
    public void recipeInstructionComparableTestSmallerOrdinal(){
        RecipeInstruction instruction1 = new RecipeInstruction(123L, 8,"Freeze!");
        RecipeInstruction instruction2 = new RecipeInstruction(123L, 9,"Freeze!");
        int comp = instruction1.compareTo(instruction2);
        assertTrue("Expected instruction1 to be smaller.", comp < 0);
        assertEquals("Expected switched comparison to give minus the original answer", -comp, instruction2.compareTo(instruction1));
    }

    @Test
    public void recipeInstructionComparableTestSmallerId(){
        RecipeInstruction instruction1 = new RecipeInstruction(5L, 1,"Freeze!");
        RecipeInstruction instruction2 = new RecipeInstruction(6L, 1,"Freeze!");
        int comp = instruction1.compareTo(instruction2);
        assertTrue("Expected instruction1 to be smaller.", comp < 0);
        assertEquals("Expected switched comparison to give minus the original answer", -comp, instruction2.compareTo(instruction1));
    }

    @Test
    public void recipeInstructionComparableTestTransitiveOrdinal(){
        RecipeInstruction instruction1 = new RecipeInstruction(10L, 1,"Freeze!");
        RecipeInstruction instruction2 = new RecipeInstruction(10L, 2,"Freeze!");
        RecipeInstruction instruction3 = new RecipeInstruction(10L, 3,"Freeze!");
        int comp12 = instruction1.compareTo(instruction2);
        int comp13 = instruction1.compareTo(instruction3);
        int comp23 = instruction2.compareTo(instruction3);
        assertTrue(comp12 < 0);
        assertTrue(comp23 < 0);
        assertTrue("Found that 1<2 and 2<3 but NOT 1<3",comp13 < 0);
    }


    @Test
    public void recipeInstructionComparableTestTransitiveId(){
        RecipeInstruction instruction1 = new RecipeInstruction(1L, 10,"Freeze!");
        RecipeInstruction instruction2 = new RecipeInstruction(2L, 10,"Freeze!");
        RecipeInstruction instruction3 = new RecipeInstruction(3L, 10,"Freeze!");
        int comp12 = instruction1.compareTo(instruction2);
        int comp13 = instruction1.compareTo(instruction3);
        int comp23 = instruction2.compareTo(instruction3);
        assertTrue(comp12 < 0);
        assertTrue(comp23 < 0);
        assertTrue("Found that 1<2 and 2<3 but NOT 1<3",comp13 < 0);
    }


}