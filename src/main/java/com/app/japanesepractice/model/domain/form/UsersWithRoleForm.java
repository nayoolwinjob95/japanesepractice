package com.app.japanesepractice.model.domain.form;

import com.app.japanesepractice.model.domain.entity.Users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsersWithRoleForm {
	private int id;
	@NotBlank(message = "Enter username.")
	private String username;
	@NotBlank(message = "Choose role.")
	private String role;

	public UsersWithRoleForm() {
	}

	public UsersWithRoleForm(Users user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole().name();
	}
}
