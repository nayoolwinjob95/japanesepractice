package com.app.japanesepractice.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.japanesepractice.model.domain.entity.Users.Role;

@Controller
@RequestMapping("/")
public class JapanesePracticeController {

	@GetMapping
	public String index() {

		var auth = SecurityContextHolder.getContext().getAuthentication();

		if (null != auth && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.Admin.name()))) {
			return "redirect:/admin/home";
		}

		return "index";
	}

	@GetMapping("/faq")
	public String viewFaq() {
		return "faq";
	}

}
