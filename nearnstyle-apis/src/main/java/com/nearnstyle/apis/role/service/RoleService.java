package com.nearnstyle.apis.role.service;

import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.dto.RoleDTO;

import java.util.List;

public interface RoleService {


        /**
         * Creates a new role
         *
         * @param roleDto The role information
         * @return The created role's DTO
         */
        RoleDTO createRole(RoleDTO roleDto);

        /**
         * Updates an existing role
         *
         * @param id The role's ID
         * @param roleDto The updated role information
         * @return The updated role's DTO
         */
        RoleDTO updateRole(Long id, RoleDTO roleDto);

        /**
         * Deletes a role
         *
         * @param roleId The role's ID
         */
        boolean deleteRole(Long roleId);

        /**
         * Gets a role by its ID
         *
         * @param roleId The role's ID
         * @return The role's DTO
         */
        RoleDTO getRoleById(Long roleId);

        /**
         * Gets a list of all roles
         *
         * @return The list of roles' DTOs
         */
        List<RoleDTO> getAllRoles();

        RoleDTO addFeatureToRole(Long roleId, Long featureId);

        RoleDTO removeFeatureFromRole(Long roleId, Long featureId);

        List<FeatureDTO> getFeaturesByRole(Long roleId);
    }


