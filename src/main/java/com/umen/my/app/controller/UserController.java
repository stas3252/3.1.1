package com.umen.my.app.controller;

import com.umen.my.app.model.Role;
import com.umen.my.app.model.User;
import com.umen.my.app.service.RoleService;
import com.umen.my.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "/")
    public String getHomePage(Model model) {
        if (roleService.getRoleByName("ROLE_ADMIN") == null) {
            roleService.addRole(new Role("ROLE_ADMIN"));
        }
        if (roleService.getRoleByName("ROLE_USER") == null) {
            roleService.addRole(new Role("ROLE_USER"));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (userService.loadUserByUsername("misha") == null) {
            userService.addUser(new User("misha", "misha", "misha@mail.ru", roles));
        }
        List<String> messages = new ArrayList<>();
        messages.add("This is Root Page");
        messages.add("Add user misha with password misha");
        model.addAttribute("messages", messages);
        return "helloPage";
    }

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "loginPage";
    }

    @GetMapping(value = "user/lk")
    public String getUserPage2(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }
    @GetMapping("user/{id}")
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }
}
