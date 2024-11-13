package com.Projeto.Receitas.controller;

import com.Projeto.Receitas.model.Image;
import com.Projeto.Receitas.repository.ImageRepository;
import com.Projeto.Receitas.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @Autowired
    private CloudStorageService cloudStorageService;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public Image uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        String link = cloudStorageService.uploadFile(image);
        Image imageData = new Image();
        imageData.setLink(link);
        return imageRepository.save(imageData);
    }
}