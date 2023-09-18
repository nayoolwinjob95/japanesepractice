package com.app.japanesepractice.model.domain.form;

import com.app.japanesepractice.annotation.NotOverlapEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsersForm {
	private String id;
	@NotBlank(message = "Enter username.")
	private String username;
	@NotBlank(message = "Enter email.")
	@NotOverlapEmail(message = "This email is already registered.")
	private String email;
	@NotBlank(message = "Enter password.")
	private String password;
}
