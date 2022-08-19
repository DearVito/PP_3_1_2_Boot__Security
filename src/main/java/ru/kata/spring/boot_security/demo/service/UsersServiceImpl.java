package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
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

    @Autowired
    public UsersServiceImpl(UsersDAO usersDAO, PasswordEncoder passwordEncoder) {
        this.usersDAO = usersDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Hibernate.initialize(user.getRoles());
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
