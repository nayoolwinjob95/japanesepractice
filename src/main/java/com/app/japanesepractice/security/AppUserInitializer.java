package com.app.japanesepractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.japanesepractice.model.domain.entity.Users;
import com.app.japanesepractice.model.domain.entity.Users.Role;
import com.app.japanesepractice.model.repository.UsersRepository;

@Component
public class AppUserInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	@Transactional
	@EventListener(classes = ContextRefreshedEvent.class)
	public void initializeUser() {
		if (usersRepository.count() == 0) {
			Users user = new Users();
			user.setUsername("Admin");
			user.setEmail("admin@mail.com");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRole(Role.Admin);
			
			usersRepository.save(user);
		}
	}

}
