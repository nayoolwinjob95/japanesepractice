package com.app.japanesepractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.japanesepractice.model.domain.entity.Users.Role;
import com.app.japanesepractice.model.domain.form.UsersWithRoleForm;
import com.app.japanesepractice.model.service.UsersService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	UsersService usersService;

	@GetMapping("/user")
	public String viewAdminUser(Model model) {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("currentUser", email);
		model.addAttribute("roles", Role.values());
		model.addAttribute("users", usersService.getUsers());
		return "admin/user";
	}

	@GetMapping("/user/search")
	public String viewAdminUser(Model model, @RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "role", required = false) String role) {
		model.addAttribute("roles", Role.values());
		model.addAttribute("users", usersService.getUsers(username, role));
		return "admin/user";
	}

	@GetMapping("/user/update/{id}")
	public String viewUpdateAdminUser(Model model, @PathVariable Integer id) {
		model.addAttribute("roles", Role.values());
		model.addAttribute("form", usersService.getUserById(id));
		return "admin/user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateAdminUser(Model model, @PathVariable Integer id,
			@Valid @ModelAttribute("form") UsersWithRoleForm usersWithRoleForm, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("roles", Role.values());
			return "admin/user/update";
		}
		usersService.update(usersWithRoleForm);
		return "redirect:/admin/user";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteAdminUser(Model model, @PathVariable Integer id) {
		usersService.delete(id);
		return "redirect:/admin/user";
	}

}
