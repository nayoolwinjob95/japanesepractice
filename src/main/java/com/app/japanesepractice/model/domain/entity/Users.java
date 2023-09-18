package com.app.japanesepractice.model.domain.entity;

import java.util.Date;

import com.app.japanesepractice.model.domain.form.UsersForm;

import lombok.Data;

@Data
public class Users {
	private int id;
	private String username;
	private String email;
	private String password;
	private Role role;
	private Date createdAt;
	private Date updatedAt;

	public Users() {
	}

	public Users(UsersForm usersForm) {
		this.username = usersForm.getUsername();
		this.email = usersForm.getEmail();
		this.password = usersForm.getPassword();
		this.role = Role.Member;
	}

	public enum Role {
		Admin, Member
	}
}
