package com.cookinghub.recipes.model.datasource.postgresql;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.recipes.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.function.LongFunction;

public class RecipeDataSourceImpl implements RecipeDataSource {

    @Autowired
    private ConnectionManager connectionManager;

    private final String DEFAULT_NAME = "NO_NAME";

    private final String INSERT_NEW_RECIPE_AND_RETURN_ID = "INSERT INTO recipes (name) VALUES (?) RETURNING id";
    private final String INSERT_NEW_INGREDIENT_AND_RETURN_ID = "INSERT INTO ingredients (name) VALUES (?) RETURNING id";
    private final String INSERT_NEW_RECIPE_INGREDIENT = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, ordinal, amount, type) VALUES (?, ?, ?, ?, CAST(? AS unit_type))";
    private final String INSERT_NEW_RECIPE_INSTRUCTION = "INSERT INTO instructions (recipe_id, ordinal, instruction) VALUES (?,?,?)";

    private final String UPDATE_RECIPE = "UPDATE recipes SET name=? WHERE id=?";
    private final String UPDATE_INGREDIENT = "UPDATE ingredients SET name=?, density=? WHERE id=?";
    private final String UPDATE_RECIPE_INGREDIENT = "UPDATE recipe_ingredients SET ingredient_id=?, amount=?, type=CAST(? AS unit_type), notes=? WHERE recipe_id=? AND ordinal=?";

    @Override
    public Recipe getRecipe(long id) {
        return null;
    }

    private <T> T createRow(String query, LongFunction<T> supplier){
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, DEFAULT_NAME);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next()) {
                    long id = rs.getLong(1);
                    return supplier.apply(id);
                } else {
                    throw new SQLException("No id was generated, could not produce " + supplier.getClass().getName());
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Recipe storeNewRecipe() {
        return createRow(INSERT_NEW_RECIPE_AND_RETURN_ID, SimpleRecipe::new);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(UPDATE_RECIPE)){
            pst.setString(1, recipe.getName());
            pst.setLong(2, recipe.getId());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(RecipeIngredient<? extends Unit> recipeIngredient : recipe.getIngredientList()){
            updateRecipeIngredient(recipeIngredient);
        }
    }

    @Override
    public Ingredient storeNewIngredient() {
        return createRow(INSERT_NEW_INGREDIENT_AND_RETURN_ID, SimpleIngredient::new);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(UPDATE_INGREDIENT)){
            if(ingredient.getDensity().isPresent()) {
                pst.setDouble(2, ingredient.getDensity().get());
            } else {
                pst.setNull(2, Types.DOUBLE);
            }
            pst.setString(1, ingredient.getName());
            pst.setLong(3, ingredient.getId());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void storeNewRecipeIngredient(long recipeId, long ingredientId, int ordinal, double amount, Unit.UnitType unitType) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(INSERT_NEW_RECIPE_INGREDIENT)) {
            pst.setLong(1, recipeId);
            pst.setLong(2, ingredientId);
            pst.setInt(3, ordinal);
            pst.setDouble(4, amount);
            pst.setString(5, unitType.name());
            pst.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeIngredient(RecipeIngredient<? extends Unit> recipeIngredient) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(UPDATE_RECIPE_INGREDIENT)){
            Ingredient ingredient = recipeIngredient.getIngredient();
            Unit unit = recipeIngredient.getAmount();
            pst.setLong(1, ingredient.getId());
            pst.setDouble(2, unit.getValue());
            pst.setString(3, unit.getUnitType().name());
            pst.setString(4, recipeIngredient.getNotes());
            pst.setLong(5, recipeIngredient.getRecipeId());
            pst.setInt(6, recipeIngredient.getOrdinal());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeNewRecipeInstruction(long recipeId, int ordinal, String instruction) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(INSERT_NEW_RECIPE_INSTRUCTION)) {
            pst.setLong(1, recipeId);
            pst.setInt(2, ordinal);
            pst.setString(3, instruction);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecipeInstruction(RecipeInstruction recipeInstruction) {
        // TODO
    }


}
