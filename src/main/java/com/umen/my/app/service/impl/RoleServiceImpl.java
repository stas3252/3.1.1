package com.umen.my.app.service.impl;

import com.umen.my.app.model.Role;
import com.umen.my.app.model.User;
import com.umen.my.app.repository.RoleRepo;
import com.umen.my.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public List<Role> allRoles() {
        return roleRepo.findAll();
    }
}
