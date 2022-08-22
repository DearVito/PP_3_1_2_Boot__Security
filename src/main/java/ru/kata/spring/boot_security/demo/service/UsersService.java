package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersService {
    void saveUser(User user, String[] roles);
    List<User> getAllUsers();
    User getUserById(Long id);
    void updateUser(Long id, User newUser);
    void deleteUserById(Long id);
    User getUserByUsername(String username);
}
