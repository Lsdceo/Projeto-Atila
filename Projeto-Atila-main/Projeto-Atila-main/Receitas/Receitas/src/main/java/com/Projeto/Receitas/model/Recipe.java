package com.Projeto.Receitas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}

