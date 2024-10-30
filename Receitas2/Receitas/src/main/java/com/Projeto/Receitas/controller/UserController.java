package com.Projeto.Receitas.controller;

import com.Projeto.Receitas.model.User;
import com.Projeto.Receitas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser (@Valid @RequestBody User user) {
        return userService.createUser (user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser ById(@PathVariable Long id) {
        return userService.getUser ById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@PathVariable Long id, @Valid @RequestBody User userDetails) {
        return userService.updateUser (id, userDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        return userService.deleteUser (id);
    }
}