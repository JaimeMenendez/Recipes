package com.jaime.recipes.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.jaime.recipes.dto.RecipeDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime date;

    @Column
    private String category;


    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> ingredients;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> directions;

    public Recipe(RecipeDto newRecipe) {
        this.setDescription(newRecipe.getDescription());
        this.setDirections(newRecipe.getDirections());
        this.setIngredients(newRecipe.getIngredients());
        this.setName(newRecipe.getName());
        this.setCategory(newRecipe.getCategory());
        this.setDate(LocalDateTime.now());
    }

    public Recipe(RecipeDto newRecipe, long id) {
        this.setDescription(newRecipe.getDescription());
        this.setDirections(newRecipe.getDirections());
        this.setIngredients(newRecipe.getIngredients());
        this.setName(newRecipe.getName());
        this.setCategory(newRecipe.getCategory());
        this.setDate(LocalDateTime.now());
        this.setId(id);
    }

}
