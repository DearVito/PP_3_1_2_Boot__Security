package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RolesService {
    List<Role> getRoles();
    Role findByName(String name);
    void saveRole(Role r);
}
