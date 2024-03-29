package com.cookinghub.recipes.model.datasource.databases;

import com.cookinghub.recipes.model.datasource.databases.postgresql.ConnectionManagerPostgres;
import com.cookinghub.recipes.model.recipes.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDataSourceImplTest {

    @Spy
    private ConnectionManager connectionManager = new ConnectionManagerPostgres();

    @InjectMocks
    private RecipeDataSourceImpl recipeDataSource;

    @Before
    public void init(){
        ReflectionTestUtils.setField(connectionManager, "host", "localhost");
        ReflectionTestUtils.setField(connectionManager, "port", "5471");
        ReflectionTestUtils.setField(connectionManager, "databaseName", "RECIPEHUB");
        ReflectionTestUtils.setField(connectionManager, "user", "postgres");
        ReflectionTestUtils.setField(connectionManager, "password", "alpine");
    }

    @Test
    public void storeNewRecipeTest() {
        Recipe recipe = recipeDataSource.storeNewRecipe();
        assertEquals("NO_NAME", recipe.getName());
        assertTrue(recipe.getId()>=0);
    }

    @Test
    public void storeNewIngredientTest() {
        Ingredient ingredient = recipeDataSource.storeNewIngredient();
        assertEquals("NO_NAME", ingredient.getName());
        assertTrue(ingredient.getId()>=0);
    }

    @Test
    public void updateIngredientTest(){
        SimpleIngredient ingredient = new SimpleIngredient("Onion",1);
        recipeDataSource.updateIngredient(ingredient);
    }

    @Test
    public void storeNewRecipeIngredientTest(){
        recipeDataSource.storeNewIngredient();
        recipeDataSource.storeNewRecipeIngredient(1,2, 1, 250., Unit.UnitType.MASS);
        recipeDataSource.storeNewRecipeIngredient(1,1, 2, 250., Unit.UnitType.MASS);
    }

    @Test
    public void storeNewRecipeInstructionTest(){
        recipeDataSource.storeNewRecipeInstruction(1,1,"It puts the lotion on its skin.");
    }

    @Test
    public void updateRecipeInstructionTest(){
        RecipeInstruction instruction = new RecipeInstruction(1,1,"EDIT: It puts the lotion on its skin.");

    }

    @Test
    public void getRecipeIngredientsTest(){
        List<RecipeIngredient<? extends Unit>> recipeIngredients = recipeDataSource.getRecipeIngredients(1);
        for(RecipeIngredient<?> recipeIngredient : recipeIngredients){
            System.out.println(recipeIngredient.toString());
        }
    }

    @Test
    public void getRecipeInstructionsTest(){
        List<RecipeInstruction> recipeInstructions = recipeDataSource.getRecipeInstructions(1);
        for(RecipeInstruction recipeInstruction : recipeInstructions){
            System.out.println(recipeInstruction.toString());
        }
    }

    @Test
    public void getRecipeTest(){
        Recipe recipe = recipeDataSource.getRecipe(1);
        System.out.println(recipe.toString());
    }
}