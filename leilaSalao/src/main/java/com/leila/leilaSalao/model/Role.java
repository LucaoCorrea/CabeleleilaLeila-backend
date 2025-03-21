package com.leila.leilaSalao.model;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.leila.leilaSalao.model.Permission.ADMIN_CREATE;
import static com.leila.leilaSalao.model.Permission.ADMIN_DELETE;
import static com.leila.leilaSalao.model.Permission.ADMIN_READ;
import static com.leila.leilaSalao.model.Permission.ADMIN_UPDATE;
import static com.leila.leilaSalao.model.Permission.CLIENT_READ;
import static com.leila.leilaSalao.model.Permission.CLIENT_UPDATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
        Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE
        )
    ),
    CLIENT(
        Set.of(
            CLIENT_READ, 
            CLIENT_UPDATE 
        )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}