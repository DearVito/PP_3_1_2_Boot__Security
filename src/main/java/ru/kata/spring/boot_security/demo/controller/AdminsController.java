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
@RequestMapping("/admin/users")
public class AdminsController {
    @Autowired
    private UsersService usersServiceImpl;
    @Autowired
    private RolesService roleService;

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", usersServiceImpl.getListOfUsers());
        return "/admin/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "/admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        usersServiceImpl.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        usersServiceImpl.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        usersServiceImpl.delete(id);
        return "redirect:/admin/users";
    }

}