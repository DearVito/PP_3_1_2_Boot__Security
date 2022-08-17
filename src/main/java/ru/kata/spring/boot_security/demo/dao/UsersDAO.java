package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersDAO {
    void save(User user);
    List<User> getListOfUsers();
    User getUserById(Long id);
    void update(Long id, User newUser);
    void delete(Long id);
    User findByUsername(String username);
}
