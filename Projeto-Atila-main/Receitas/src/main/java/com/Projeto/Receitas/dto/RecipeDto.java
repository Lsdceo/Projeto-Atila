package com.Projeto.Receitas.dto;

import com.Projeto.Receitas.model.User;


public record RecipeDto(String title, String Description, User user) {
}
