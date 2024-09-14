package com.jp.annoation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jp.users.UserEntity;
import com.jp.users.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UsernameValidation, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		Optional<UserEntity> optUserEntity = userRepository.findByUsername(username);
		if (optUserEntity.isPresent()) {
			System.out.println("Janardhan");
			return false;
		} else {
			return true;
		}
	}

}
