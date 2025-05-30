package com.codigo.ms_sistem_sif.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "\"user\"")
public class UsuarioEntity implements UserDetails {
    @Id
    @Column(nullable = false, unique = true)
    private Long codUsuario;

    private String nomUsuario;
    private String cagUsuario;
    private Integer stdUsuario;

    @Column(unique = true)
    private String email;

    private String password;

    @JoinTable(name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rol> roles = new HashSet<>();

    // Relación One-to-Many con Incidencia (como trabajador asignado)
    @OneToMany(mappedBy = "trabajador")

    private List<IncidenciaEntity> incidenciasAsignadas;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
