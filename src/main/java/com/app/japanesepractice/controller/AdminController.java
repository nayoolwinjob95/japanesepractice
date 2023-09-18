package com.app.japanesepractice.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/home")
	public String viewAdminHome(Model model) {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("currentUser", email);
		return "admin/home";
	}

}
