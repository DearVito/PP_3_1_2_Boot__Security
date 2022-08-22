package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UsersDAO;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersDAO usersDAO;
    PasswordEncoder passwordEncoder;
    private RolesService rolesService;

    @Autowired
    public UsersServiceImpl(UsersDAO usersDAO, PasswordEncoder passwordEncoder, RolesService rolesService) {
        this.usersDAO = usersDAO;
        this.passwordEncoder = passwordEncoder;
        this.rolesService = rolesService;
    }

    @Transactional
    public void saveUser(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        for (String role: roles) {
            user.addRole(rolesService.getRoleByName(role));
        }
        usersDAO.saveUser(user);
    }
    public List<User> getAllUsers() {
        return usersDAO.findAll();
    }
    public User getUserById(Long id) {
        return usersDAO.findUserById(id);
    }
    @Transactional
    public void updateUser(Long id, User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersDAO.updateUser(id, newUser);
    }
    @Transactional
    public void deleteUserById(Long id) {
        usersDAO.deleteUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return usersDAO.findUserByUsername(username);
    }
}
