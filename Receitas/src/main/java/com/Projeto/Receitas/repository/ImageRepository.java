package com.Projeto.Receitas.repository;
import com.Projeto.Receitas.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}