package com.revanya.apps.services.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // this should match the role name in Keycloak

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    //... potentially other fields...
}

