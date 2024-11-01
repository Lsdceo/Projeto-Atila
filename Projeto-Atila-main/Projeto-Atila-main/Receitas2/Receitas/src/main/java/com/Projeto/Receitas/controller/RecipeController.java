package com.Projeto.Receitas.controller;

import com.Projeto.Receitas.model.Recipe;
import com.Projeto.Receitas.model.User;
import com.Projeto.Receitas.repository.RecipeRepository;
import com.Projeto.Receitas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        Optional<User> user = userRepository.findById(recipe.getUser().getId());
        if (user.isPresent()) {
            recipe.setUser(user.get());
            recipeRepository.save(recipe);
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipeDetails) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setTitle(recipeDetails.getTitle());
                    recipe.setDescription(recipeDetails.getDescription());
                    return ResponseEntity.ok(recipeRepository.save(recipe));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
