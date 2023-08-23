package com.nearnstyle.apis.role.dto;

import com.nearnstyle.apis.role.model.Role.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private State state;
    private List<FeatureDTO> features;

}


