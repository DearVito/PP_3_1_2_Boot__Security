package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    @Autowired
    private UsersService usersServiceImpl;
    @Autowired
    private RolesService roleService;
    @GetMapping
    public String adminsPage() {
        return "/admin/admin";
    }
    @GetMapping("/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", usersServiceImpl.getListOfUsers());
        return "/admin/users";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "/show";
    }

    @GetMapping("/users/add")
    public String newUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("roles",roleService.getRoles());
        return "/admin/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user, @RequestParam("rolesList") String[] roles) {
        for (String role: roles) {
            user.addRole(roleService.findByName(role));
        }
        usersServiceImpl.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        model.addAttribute("roles",roleService.getRoles());
        return "/admin/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user,
                         @RequestParam("rolesList") String[] roles) {
        for (String role: roles) {
            user.addRole(roleService.findByName(role));
        }
        usersServiceImpl.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        usersServiceImpl.delete(id);
        return "redirect:/admin/users";
    }

}