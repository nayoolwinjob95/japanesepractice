package com.app.japanesepractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.app.japanesepractice.model.domain.entity.Users.Role;
import com.app.japanesepractice.model.service.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class JapanesepracticeSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {

		http.formLogin(form -> form.loginPage("/users/signin").defaultSuccessUrl("/"));

		http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/resources/**", "/", "/ebook/**", "/practice", "/faq", "/test/authenticate")
				.permitAll().requestMatchers("/users/signin", "/users/signup").anonymous().requestMatchers("/admin/**")
				.hasAnyAuthority(Role.Admin.name()).anyRequest().authenticated());

		http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/denied-page"));

		return http.build();
	}

	@Bean
	AuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
