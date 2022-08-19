package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersDAO {
    void saveUser(User user);
    List<User> findAll();
    User findUserById(Long id);
    void updateUser(Long id, User newUser);
    void deleteUserById(Long id);
    User findUserByUsername(String username);
}
