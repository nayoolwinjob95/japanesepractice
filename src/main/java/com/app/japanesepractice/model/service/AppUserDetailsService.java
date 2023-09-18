package com.app.japanesepractice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.japanesepractice.model.repository.UsersRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return usersRepository.findOneByEmail(email)
				.map(user -> User.withUsername(email).password(user.getPassword())
						.authorities(AuthorityUtils.createAuthorityList(user.getRole().name())).build())
				.orElseThrow(
						() -> new UsernameNotFoundException("There is no user with this email - %s.".formatted(email)));
	}

}
