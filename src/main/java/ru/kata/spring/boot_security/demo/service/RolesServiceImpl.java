package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    public List<Role> getAllPossibleRoles() {
        return roleDAO.getAllPossibleRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    @Override
    @Transactional
    public void saveRole(Role r) {
        roleDAO.saveRole(r);
    }
}
