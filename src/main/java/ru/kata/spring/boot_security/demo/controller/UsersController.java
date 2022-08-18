package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {
	@Autowired
	private UsersService usersServiceImpl;
	@Autowired
	private RolesService roleService;
	@GetMapping()
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		model.addAttribute("messages", messages);
		return "index";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	@GetMapping("/user")
	public String getUsersPage(Model model, Principal principal) {
		User user = usersServiceImpl.findByUsername(principal.getName());
		model.addAttribute("user",user);
		return "show";
	}
	
}