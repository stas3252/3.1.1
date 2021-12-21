package com.umen.my.app.service;

import com.umen.my.app.model.Role;
import com.umen.my.app.model.User;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    List<Role> allRoles();

}
