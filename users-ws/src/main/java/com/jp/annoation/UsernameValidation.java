package com.jp.annoation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = { UserNameValidator.class})
@Target({FIELD })
@Retention(RUNTIME)
public @interface UsernameValidation {

	String message() default "Users Already In Place. Please enter valid Username";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	
}
