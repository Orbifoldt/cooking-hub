package com.cookinghub.recipes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SearchController {

    @RequestMapping("/search")
    public List<Long> searchRecipe(
                               @RequestParam(name="recipeName") String recipeName,
                               @RequestParam(name="ingredientNames")List<String> ingredients){
        return new ArrayList<>(Arrays.asList(1l,2l));
    }
}
