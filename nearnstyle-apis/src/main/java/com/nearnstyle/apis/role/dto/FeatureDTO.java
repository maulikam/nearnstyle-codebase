package com.nearnstyle.apis.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
}

