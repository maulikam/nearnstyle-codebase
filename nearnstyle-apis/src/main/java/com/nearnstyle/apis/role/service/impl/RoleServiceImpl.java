package com.nearnstyle.apis.role.service.impl;


import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.dto.RoleDTO;
import com.nearnstyle.apis.role.mapper.FeatureMapper;
import com.nearnstyle.apis.role.mapper.RoleMapper;
import com.nearnstyle.apis.role.model.Role;
import com.nearnstyle.apis.role.repository.RoleRepository;
import com.nearnstyle.apis.role.service.FeatureService;
import com.nearnstyle.apis.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FeatureService featureService;


    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return RoleMapper.convertRoleMasterListToDtoList(roles);
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDto) {

        Optional<Role> roleOptional = roleRepository.findByName(roleDto.getName());
        if (roleOptional.isPresent()) {
            throw new IllegalStateException("Role already exists");
        }

        Role role = new Role();
        role = RoleMapper.convertRoleDtoToMaster(roleDto, role);
        return RoleMapper.convertRoleMasterToDto(roleRepository.save(role));
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDto) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleRepository.save(RoleMapper.convertRoleDtoToMaster(roleDto, roleOptional.get()));
            return RoleMapper.convertRoleMasterToDto(role);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteRole(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            roleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new ResourceNotFoundException("Role with id " + roleId + " not found.");
        }
        return RoleMapper.convertRoleMasterToDto(role);
    }

    public RoleDTO addFeatureToRole(Long roleId, Long featureId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + roleId));

        FeatureDTO featureDto = featureService.getFeatureById(featureId);

        role.getFeatures().add(FeatureMapper.convertDtoToFeature(featureDto));
        role = roleRepository.save(role);

        return RoleMapper.convertRoleMasterToDto(role);
    }

    public RoleDTO removeFeatureFromRole(Long roleId, Long featureId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + roleId));
        role.getFeatures().removeIf(feature -> feature.getId().equals(featureId));
        role = roleRepository.save(role);

        return RoleMapper.convertRoleMasterToDto(role);
    }

    public List<FeatureDTO> getFeaturesByRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + roleId));

        return role.getFeatures().stream()
                .map(FeatureMapper::convertFeatureToDto)
                .collect(Collectors.toList());
    }

}
