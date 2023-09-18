package com.app.japanesepractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.japanesepractice.model.domain.form.UsersForm;
import com.app.japanesepractice.model.service.UsersService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersService usersService;

	@GetMapping("/signin")
	public String viewSignIn(Model model) {
		return "signin";
	}

	@GetMapping("/signup")
	public String viewSignUp(Model model) {
		model.addAttribute("form", new UsersForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String signUp(Model model, @Valid @ModelAttribute("form") UsersForm usersForm, BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}
		usersService.save(usersForm);
		return "redirect:/";
	}

}
