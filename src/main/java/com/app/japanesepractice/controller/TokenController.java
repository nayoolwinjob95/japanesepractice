package com.app.japanesepractice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.japanesepractice.model.domain.entity.Users;
import com.app.japanesepractice.model.domain.form.AuthRequestForm;
import com.app.japanesepractice.model.service.EmailSenderService;
import com.app.japanesepractice.model.service.JwtService;
import com.app.japanesepractice.model.service.UsersService;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	EmailSenderService emailSenderService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String viewAuthenticateAndGetToken(Model model) {
		return "token";
	}

	@PostMapping
	@PreAuthorize("authenticated()")
	public String authenticateAndGetToken(Model model,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "bookId", required = false) String bookId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Users> user = usersService.getOneByEmail(email);
		String encodedPassword = user.get().getPassword();

		if (passwordEncoder.matches(password, encodedPassword)) {
			AuthRequestForm auth = new AuthRequestForm();
			auth.setUsername(email);
			auth.setPassword(password);
			//String token = jwtService.generateToken(auth.getUsername());
			String token = jwtService.generateToken(bookId);
			emailSenderService.sendEmail(email, "Get token with sharable URL", "Your token - " + token);
		} else {
			model.addAttribute("param", bookId);
			model.addAttribute("invalid", "Invalid request - Authentication Fail.");
			return "token";
		}
		return "redirect:/token/confirm";
	}
	
	@GetMapping("/confirm")
	public String viewTokenConfirm(Model model) {
		return "token-confirm";
	}
}
