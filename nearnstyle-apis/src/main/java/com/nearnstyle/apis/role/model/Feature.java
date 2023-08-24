package com.nearnstyle.apis.role.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ns_feature_master")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "description", nullable = true, length = 300)
    private String description;

    @ManyToMany(mappedBy = "features")
    private List<Role> roles;
}

