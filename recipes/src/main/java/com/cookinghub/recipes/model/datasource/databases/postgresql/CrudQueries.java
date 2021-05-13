package com.cookinghub.recipes.model.datasource.databases.postgresql;

public final class CrudQueries {

    public static final String INSERT_NEW_RECIPE_AND_RETURN_ID = "INSERT INTO recipes (name) VALUES (?) RETURNING id";
    public static final String INSERT_NEW_INGREDIENT_AND_RETURN_ID = "INSERT INTO ingredients (name) VALUES (?) RETURNING id";
    public static final String INSERT_NEW_RECIPE_INGREDIENT = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, ordinal, amount, type) VALUES (?, ?, ?, ?, CAST(? AS unit_type))";
    public static final String INSERT_NEW_RECIPE_INSTRUCTION = "INSERT INTO instructions (recipe_id, ordinal, instruction) VALUES (?,?,?)";

    public static final String UPDATE_RECIPE = "UPDATE recipes SET name=? WHERE id=?";
    public static final String UPDATE_INGREDIENT = "UPDATE ingredients SET name=?, density=? WHERE id=?";
    public static final String UPDATE_RECIPE_INGREDIENT = "UPDATE recipe_ingredients SET ingredient_id=?, amount=?, type=CAST(? AS unit_type), notes=? WHERE recipe_id=? AND ordinal=?";
    public static final String UPDATE_RECIPE_INSTRUCTION = "UPDATE instructions SET ordinal=?, instruction=? WHERE recipe_id=?";

    public static final String GET_RECIPE = "SELECT name, created_at FROM recipes WHERE id=?";
    public static final String GET_INGREDIENT = "SELECT name, density FROM ingredients WHERE id=?";
    public static final String GET_RECIPE_INGREDIENTS = "SELECT ordinal, ingredient_id, amount, type, notes FROM recipe_ingredients WHERE recipe_id=?";
    public static final String GET_RECIPE_INSTRUCTIONS = "SELECT ordinal, instruction FROM instructions WHERE recipe_id=?";

    public static final String DELETE_RECIPE = "DELETE FROM recipes WHERE id=?";
    public static final String DELETE_INGREDIENT = "DELETE FROM ingredients WHERE id=?";
    public static final String DELETE_RECIPE_INGREDIENT = "DELETE FROM recipe_ingredients WHERE recipe_id=? AND ordinal=?";
    public static final String DELETE_RECIPE_INSTRUCTION = "DELETE FROM instructions WHERE recipe_id=? AND ordinal=?";

}
