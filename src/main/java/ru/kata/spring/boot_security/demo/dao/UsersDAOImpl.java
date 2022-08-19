package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    public void saveUser(User user) {
        if (entityManager.contains(user)) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }
    public void updateUser(Long id, User newUser) {
        User oldUser = findUserById(id);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setRoles(newUser.getRoles());
        Hibernate.initialize(newUser.getRoles());
        entityManager.merge(oldUser);
    }
    public void deleteUserById(Long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public User findUserByUsername(String username) {
        User user = entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Hibernate.initialize(user.getRoles());
        return user;
    }
}
