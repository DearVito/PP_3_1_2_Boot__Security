package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Role> getAllPossibleRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery(
                        "SELECT u from Role u WHERE u.name = :name", Role.class).
                setParameter("name", name).getSingleResult();
    }

    @Override
    public void saveRole(Role r) {
        entityManager.persist(r);
    }
}
