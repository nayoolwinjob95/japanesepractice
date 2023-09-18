package com.app.japanesepractice.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.japanesepractice.model.service.UsersService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEnoughChoicesValidator implements ConstraintValidator<NotEnoughChoices, String> {

	@Autowired
	UsersService usersService;

	@Override
	public boolean isValid(String choices, ConstraintValidatorContext cxt) {
		String[] choicesArr = choices.split(",");
        return choicesArr.length == 4 ? true : false;
    }
}
