package com.nearnstyle.apis.role.repository.impl;

import com.nearnstyle.apis.role.model.Role;

import java.util.List;

public interface RoleRepositoryCustom {

    List<Role> findAll(boolean isActive);
}
