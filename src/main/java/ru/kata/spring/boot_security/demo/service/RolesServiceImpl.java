package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Override
    @Transactional
    public Role findById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public void saveRole(Role r) {
        roleDAO.saveRole(r);
    }
}
