package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UsersDAO;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private UsersDAO usersDAOImpl;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersDAO userDao, PasswordEncoder passwordEncoder) {
        this.usersDAOImpl = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDAOImpl.save(user);
    }
    public List<User> getListOfUsers() {
        return usersDAOImpl.getListOfUsers();
    }
    public User getUserById(Long id) {
        return usersDAOImpl.getUserById(id);
    }
    @Transactional
    public void update(Long id, User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersDAOImpl.update(id, newUser);
    }
    @Transactional
    public void delete(Long id) {
        usersDAOImpl.delete(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return usersDAOImpl.findByUsername(username);
    }
}
