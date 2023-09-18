package com.app.japanesepractice.annotation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.japanesepractice.model.domain.entity.Users;
import com.app.japanesepractice.model.service.UsersService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotOverlapEmailValidator implements ConstraintValidator<NotOverlapEmail, String> {

	@Autowired
	UsersService usersService;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
        Optional<Users> user = usersService.getOneByEmail(email);
        return user.isEmpty();
    }
}
