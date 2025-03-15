package com.leila.leilaSalao.token;

import com.leila.leilaSalao.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique = true, nullable = false) 
    private String token;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false) 
    private TokenType tokenType = TokenType.BEARER;

    @Column(nullable = false) 
    private boolean revoked;

    @Column(nullable = false)
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;
}