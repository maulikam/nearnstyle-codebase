package com.nearnstyle.apis.role.controller;

import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.dto.RoleDTO;
import com.nearnstyle.apis.role.service.RoleService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value = "id") Long roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PostMapping
    public ResponseEntity<Object> createRole(@RequestBody RoleDTO roleDto) {

        try {
            return ResponseEntity.ok(roleService.createRole(roleDto));
        } catch (IllegalStateException expected) {
            return ResponseEntity.badRequest().body("Role already exists!");
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable(value = "id") Long id, @RequestBody RoleDTO roleDto) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDto));
    }

    @PostMapping("/{roleId}/features/{featureId}")
    public ResponseEntity<RoleDTO> addFeatureToRole(@PathVariable Long roleId, @PathVariable Long featureId) {
        return ResponseEntity.ok(roleService.addFeatureToRole(roleId, featureId));
    }

    @DeleteMapping("/{roleId}/features/{featureId}")
    public ResponseEntity<RoleDTO> removeFeatureFromRole(@PathVariable Long roleId, @PathVariable Long featureId) {
        return ResponseEntity.ok(roleService.removeFeatureFromRole(roleId, featureId));
    }

    @GetMapping("/{roleId}/features")
    public ResponseEntity<List<FeatureDTO>> getFeaturesByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(roleService.getFeaturesByRole(roleId));
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deleteRole(@PathVariable(value = "id") Long roleId) {
//        boolean isDeleted = roleService.deleteRole(roleId);
//        return ResponseEntity.ok(isDeleted);
//    }

}
