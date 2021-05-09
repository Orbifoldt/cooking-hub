package com.cookinghub.recipes.model.datasource.postgresql;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.recipes.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final String UPDATE_RECIPE_INSTRUCTION = "UPDATE instructions SET ordinal=?, instruction=? WHERE recipe_id=?";

    private final String GET_RECIPE = "SELECT name, created_at FROM recipes WHERE id=?";
    private final String GET_INGREDIENT = "SELECT name, density FROM ingredients WHERE id=?";
    private final String GET_RECIPE_INGREDIENTS = "SELECT ordinal, ingredient_id, amount, type, notes FROM recipe_ingredients WHERE recipe_id=?";
    private final String GET_RECIPE_INSTRUCTIONS = "SELECT ordinal, instruction FROM instructions WHERE recipe_id=?";

    private final String DELETE_RECIPE = "DELETE FROM recipes WHERE id=?";
    private final String DELETE_INGREDIENT = "DELETE FROM ingredients WHERE id=?";
    private final String DELETE_RECIPE_INGREDIENT = "DELETE FROM recipe_ingredients WHERE recipe_id=? AND ordinal=?";
    private final String DELETE_RECIPE_INSTRUCTION = "DELETE FROM instructions WHERE recipe_id=? AND ordinal=?";

    @Override
    public Recipe getRecipe(long id) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(GET_RECIPE)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String name = rs.getString(1);
                Date date = rs.getDate(2);
                Recipe recipe = new SimpleRecipe(name, id);
                List<RecipeIngredient<?>> recipeIngredients = getRecipeIngredients(id);
                for(RecipeIngredient<?> recipeIngredient : recipeIngredients){
                    recipe.addIngredient(recipeIngredient);
                }
                List<RecipeInstruction> recipeInstructions = getRecipeInstructions(id);
                for(RecipeInstruction recipeInstruction : recipeInstructions){
                    recipe.addInstruction(recipeInstruction.getInstruction());
                }
                return recipe;
            } else {
                throw new SQLException(String.format("No recipe with id=%d was found", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteRecipe(long id) {
        executeWithLongArgument(DELETE_RECIPE, id);
    }

    @Override
    public Ingredient getIngredient(long id) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(GET_INGREDIENT)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String name = rs.getString(1);
                double densityValue = rs.getDouble(2);
                Optional<Double> density = Optional.ofNullable(densityValue > 0 ? densityValue : null);
                return new SimpleIngredient(name, id, density);
            } else {
                throw new SQLException(String.format("No ingredient with id=%d was found", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteIngredient(long id) {
        executeWithLongArgument(DELETE_INGREDIENT, id);
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
    public List<RecipeIngredient<? extends Unit>> getRecipeIngredients(long recipeId) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(GET_RECIPE_INGREDIENTS)) {
            pst.setLong(1, recipeId);
            ResultSet rs = pst.executeQuery();
            List<RecipeIngredient<? extends Unit>> recipeIngredients = new ArrayList<>();
            while(rs.next()){
                int ordinal = rs.getInt(1);
                long ingredientId = rs.getLong(2);
                double amountValue = rs.getDouble(3);
                String type = rs.getString(4);
                String notes = rs.getString(5);

                Unit.UnitType unitType = Unit.UnitType.valueOf(type);
                Ingredient ingredient = getIngredient(ingredientId);
                Unit amount = Units.produceUnit(unitType, amountValue);
                RecipeIngredient<? extends Unit> recipeIngredient = new RecipeIngredient<>(ingredient, amount, recipeId, ordinal);
                recipeIngredient.setNotes(notes);
                recipeIngredients.add(recipeIngredient);
            }
            if(recipeIngredients.isEmpty()) {
                throw new SQLException(String.format("No recipe ingredient for the recipe with recipe_id=%d was found", recipeId));
            } else {
                return recipeIngredients;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void deleteRecipeIngredient(long recipeId, int ordinal) {
        executeWithLongAndIntArgument(DELETE_RECIPE_INGREDIENT, recipeId, ordinal);
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
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(UPDATE_RECIPE_INSTRUCTION)) {
            pst.setInt(1, recipeInstruction.getOrdinal());
            pst.setString(2, recipeInstruction.getInstruction());
            pst.setLong(3, recipeInstruction.getRecipeId());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RecipeInstruction> getRecipeInstructions(long recipeId) {
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(GET_RECIPE_INSTRUCTIONS)) {
            pst.setLong(1, recipeId);
            ResultSet rs = pst.executeQuery();
            List<RecipeInstruction> recipeInstructions = new ArrayList<>();
            while(rs.next()){
                int ordinal = rs.getInt(1);
                String instruction = rs.getString(2);
                recipeInstructions.add(new RecipeInstruction(recipeId, ordinal, instruction));
            }
            if(recipeInstructions.isEmpty()) {
                throw new SQLException(String.format("No recipe instruction for the recipe with recipe_id=%d was found", recipeId));
            } else {
                return recipeInstructions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteRecipeInstruction(long recipeId, int ordinal) {
        executeWithLongAndIntArgument(DELETE_RECIPE_INSTRUCTION, recipeId, ordinal);
    }


    private void executeWithLongArgument(String query, long argument){
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, argument);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeWithLongAndIntArgument(String query, long argument1, int argument2){
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, argument1);
            pst.setInt(2, argument2);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


}
