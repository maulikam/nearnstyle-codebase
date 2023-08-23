package com.nearnstyle.apis.role.mapper;

import com.nearnstyle.apis.role.dto.RoleDTO;
import com.nearnstyle.apis.role.model.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDTO convertRoleMasterToDto(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName() != null ? role.getName() : "");
        roleDTO.setDescription(role.getDescription() != null ? role.getDescription() : "");
        roleDTO.setState(role.getState());
        roleDTO.setFeatures(FeatureMapper.convertFeatureListToDtoList(role.getFeatures())); // Map features

        return roleDTO;
    }

    public static Role convertRoleDtoToMaster(RoleDTO roleDTO, Role role) {
        if (roleDTO == null) {
            return null;
        }

        role.setName(roleDTO.getName() != null ? roleDTO.getName() : "");
        role.setDescription(roleDTO.getDescription() != null ? roleDTO.getDescription() : "");
        role.setState(roleDTO.getState());
        role.setFeatures(FeatureMapper.convertFeatureDtoListToList(roleDTO.getFeatures())); // Map features

        return role;
    }

    public static List<RoleDTO> convertRoleMasterListToDtoList(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .filter(role -> role != null)
                .map(RoleMapper::convertRoleMasterToDto)
                .collect(Collectors.toList());
    }

    public static List<Role> convertRoleDtoListToMasterList(List<RoleDTO> roleDTOs) {
        if (roleDTOs == null) {
            return null;
        }
        return roleDTOs.stream()
                .filter(roleDTO -> roleDTO != null)
                .map(roleDTO -> convertRoleDtoToMaster(roleDTO, new Role()))
                .collect(Collectors.toList());
    }
}
