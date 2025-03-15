package com.leila.leilaSalao.appointment;

import java.time.LocalDateTime;

import com.leila.leilaSalao.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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