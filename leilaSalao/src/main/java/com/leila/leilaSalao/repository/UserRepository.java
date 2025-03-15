package com.leila.leilaSalao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leila.leilaSalao.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
