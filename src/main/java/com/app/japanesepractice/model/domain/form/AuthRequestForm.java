package com.app.japanesepractice.model.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestForm {
	@NotBlank(message = "Enter username.")
	private String username;
	@NotBlank(message = "Enter password.")
	private String password;
}
