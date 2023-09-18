package com.app.japanesepractice.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.japanesepractice.model.domain.entity.Users;
import com.app.japanesepractice.model.domain.form.UsersForm;
import com.app.japanesepractice.model.domain.form.UsersWithRoleForm;
import com.app.japanesepractice.model.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ResultService resultService;

	@Transactional
	public void save(UsersForm usersForm) {
		usersForm.setPassword(passwordEncoder.encode(usersForm.getPassword()));
		usersRepository.save(new Users(usersForm));
	}

	public Optional<Users> getOneByEmail(String email) {
		return usersRepository.findOneByEmail(email);
	}

	public List<Users> getUsers() {
		return usersRepository.getUsers();
	}

	public List<Users> getUsers(String username, String role) {
		return usersRepository.getUsers(username, role);
	}

	public void delete(Integer id) {
		resultService.deleteByUserId(id);
		usersRepository.delete(id);
	}

	public UsersWithRoleForm getUserById(Integer id) {
		Users user = usersRepository.getUserById(id);
		return new UsersWithRoleForm(user);
	}

	public void update(UsersWithRoleForm usersWithRoleForm) {
		usersRepository.update(usersWithRoleForm);
	}

}
