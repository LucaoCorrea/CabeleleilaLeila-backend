package com.leila.leilaSalao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leila.leilaSalao.model.UserResponse;
import com.leila.leilaSalao.security.LoginRequest;
import com.leila.leilaSalao.security.RegisterRequest;
import com.leila.leilaSalao.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest request) {
        System.out.println("Dados recebidos: " + request); // Log dos dados recebidos
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        System.out.println("Dados recebidos: " + request); // Log dos dados recebidos
        return ResponseEntity.ok(service.login(request));
    }
}