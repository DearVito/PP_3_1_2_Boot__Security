package ru.kata.spring.boot_security.demo.dao;

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
    public List<User> getListOfUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    public void save(User user) {
        if (entityManager.contains(user)) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }
    public void update(Long id, User newUser) {
        User oldUser = getUserById(id);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setRoles(newUser.getRoles());
        entityManager.merge(oldUser);
    }
    public void delete(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User findByUsername(String username) {
        User user = entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
