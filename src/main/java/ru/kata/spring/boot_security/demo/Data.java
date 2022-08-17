package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import javax.annotation.PostConstruct;

@Component
public class Data {
    @Autowired
    private UsersService userService;
    @Autowired
    private RolesService roleService;

    @PostConstruct
    private void postConstruct() {
        String[] rolesAdmin = {"ROLE_ADMIN", "ROLE_USER"};
        Role userRole = new Role(rolesAdmin[1]);
        Role admRole = new Role(rolesAdmin[0]);
        roleService.saveRole(admRole);
        roleService.saveRole(userRole);

        User user = new User();
        user.setId(1L);
        user.setUsername("User");
        user.setPassword("111");
        user.setEmail("u@mail.ru");

        User admin = new User();
        admin.setId(2L);
        admin.setUsername("Admin");
        admin.setPassword("111");
        admin.setEmail("adm@mail.ru");

        user.addRole(userRole);
        admin.addRole(userRole);
        admin.addRole(admRole);
        userService.save(user);
        userService.save(admin);
    }
}
