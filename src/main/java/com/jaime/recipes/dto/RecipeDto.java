package com.jaime.recipes.dto;

import lombok.*;
import com.jaime.recipes.model.Recipe;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    @NotBlank(message = "Name should not be blank.")
    @NotNull(message = "Name should not be null.")
    private String name;

    @NotBlank(message = "Description should not be blank.")
    @NotNull(message = "Description should not be null.")
    private String description;

    @NotNull(message = "Ingredients should not be null.")
    @Size(min = 1, message = "Ingredients array should contain at least one item")
    private List<String> ingredients;

    @NotNull(message = "Directions should not be null.")
    @Size(min = 1, message = "Directions array should contain at least one item")
    private List<String> directions;

    private LocalDateTime date;

    @NotNull(message = "Category should not be null.")
    @NotBlank(message = "Category should not be blank.")
    private String category;

    public RecipeDto(Recipe recipe) {
        this.name = recipe.getName();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.directions = recipe.getDirections();
        this.date = recipe.getDate();
        this.category = recipe.getCategory();
    }

}
