package com.leila.leilaSalao.appointment;

import java.time.LocalDateTime;

import com.leila.leilaSalao.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "O serviço é obrigatório.")
    @Column(nullable = false)
    private String service;

    @NotNull(message = "A data é obrigatória.")
    @Column(nullable = false)
    private LocalDateTime date;

    @NotBlank(message = "O status é obrigatório.")
    @Column(nullable = false)
    private String status;
}