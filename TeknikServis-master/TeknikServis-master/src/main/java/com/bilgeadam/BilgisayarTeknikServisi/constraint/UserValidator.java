package com.bilgeadam.BilgisayarTeknikServisi.constraint;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SystemUser.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SystemUser systemUser = (SystemUser) o;

		if (systemUser.getUsername().length() < 6) {
			errors.rejectValue("username", "size_username_too_short", "username 6 karakterden küçük olamaz");
		}
		if (systemUser.getUsername().length() > 32) {
			errors.rejectValue("username", "size_username_too_long", "username 32 karakterden büyük olamaz");
		}
		if (systemUser.getPassword().length() < 8) {
			errors.rejectValue("password", "size_password_too_short", "şifre 8 karakterden küçük olamaz.");
		}
		if (systemUser.getPassword().length() > 100) {
			errors.rejectValue("password", "size_password_too_long", "şifre 100 karakterden büyük olamaz.");
		}
	}

}
