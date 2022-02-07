package com.jaime.recipes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.jaime.recipes.dto.RecipeDto;
import com.jaime.recipes.model.Recipe;
import com.jaime.recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> addNewRecipe(@Valid @RequestBody RecipeDto newRecipe,
                                                          @AuthenticationPrincipal UserDetails user) {
        Recipe recipe = new Recipe(newRecipe);
        recipe.setUsername(user.getUsername());

        long id = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(new RecipeDto(recipe));
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails user) {
        recipeService.deleteRecipe(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable long id, @Valid @RequestBody RecipeDto recipeBody,
                                          @AuthenticationPrincipal UserDetails user) {
        Recipe recipe = new Recipe(recipeBody, id);
        recipe.setUsername(user.getUsername());

        recipeService.updateRecipe(recipe, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<?> searchRecipe(@RequestParam Map<String, String> allRequestParams) {
        if (allRequestParams.size() != 1) {
            return ResponseEntity.badRequest().build();
        }
        if (allRequestParams.containsKey("category")) {
            return ResponseEntity.ok(recipeService
                    .findAllRecipesByCategory(allRequestParams.get("category"))
                    .stream().map(RecipeDto::new)
                    .collect(Collectors.toList()));
        }

        if (allRequestParams.containsKey("name")) {
            return ResponseEntity.ok(recipeService.
                    findAllRecipesByName(allRequestParams.get("name"))
                    .stream().map(RecipeDto::new)
                    .collect(Collectors.toList()));
        }

        return ResponseEntity.badRequest().build();

    }
}
