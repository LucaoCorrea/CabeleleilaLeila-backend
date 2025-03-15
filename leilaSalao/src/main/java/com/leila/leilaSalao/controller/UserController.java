package com.leila.leilaSalao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leila.leilaSalao.model.User;
import com.leila.leilaSalao.model.UserResponse;
import com.leila.leilaSalao.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/hello")
    public String response() {
        return "Ok";
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserData(Principal connectedUser) {
        return ResponseEntity.ok(service.getCurrentUser(connectedUser));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }
}