package com.app.japanesepractice.model.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.app.japanesepractice.model.domain.entity.Users;
import com.app.japanesepractice.model.domain.form.UsersWithRoleForm;

@Mapper
public interface UsersRepository {

	void save(Users user);

	Optional<Users> findOneByEmail(String email);

	int count();

	List<Users> getUsers();

	List<Users> getUsers(@Param("username") String username, @Param("role") String role);

	void delete(Integer id);

	Users getUserById(Integer id);

	void update(@Param("user") UsersWithRoleForm usersWithRoleForm);

}
