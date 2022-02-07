package com.jaime.recipes.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.jaime.recipes.error.RecipeNotFoundException;
import com.jaime.recipes.error.UnauthorizedException;
import com.jaime.recipes.model.Recipe;
import com.jaime.recipes.repository.RecipeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipe(long id) {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    @Transactional
    @Modifying
    public long saveRecipe(Recipe newRecipe) {
        Recipe recipe = recipeRepository.save(newRecipe);
        return recipe.getId();
    }

    @Transactional
    @Modifying
    public void deleteRecipe(long id, UserDetails user) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        if (recipe.getUsername().equals(user.getUsername())) {
            recipeRepository.deleteById(id);
        } else {
            throw new UnauthorizedException();
        }
    }

    public List<Recipe> findAllRecipesByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findAllRecipesByName(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    @Transactional
    @Modifying
    public void updateRecipe(Recipe recipe, UserDetails user) {
        Recipe recipeDB = recipeRepository.findById(recipe.getId())
                .orElseThrow(RecipeNotFoundException::new);

        if (recipeDB.getUsername().equals(user.getUsername())) {
            recipeRepository.save(recipe);
        } else {
            throw new UnauthorizedException();
        }
    }
}
