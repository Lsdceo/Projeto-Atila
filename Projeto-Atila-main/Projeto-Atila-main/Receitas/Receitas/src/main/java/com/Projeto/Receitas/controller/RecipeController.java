package com.Projeto.Receitas.controller;


import com.Projeto.Receitas.model.Recipe;
import com.Projeto.Receitas.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<Recipe> listar() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Recipe> criar(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeRepository.save(recipe), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> buscarPorId(@PathVariable Long idi) {
       Recipe recipe = new Recipe();
               recipeRepository.findById(idi);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long idi) {
        recipeRepository.deleteById(idi);
        return ResponseEntity.noContent().build();
    }
}
