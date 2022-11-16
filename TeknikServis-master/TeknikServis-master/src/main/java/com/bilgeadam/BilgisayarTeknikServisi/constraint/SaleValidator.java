package com.bilgeadam.BilgisayarTeknikServisi.constraint;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;

@Component
public class SaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Sale.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Sale sale = (Sale) o;
		if (sale.getNote().length() < 5) {
			errors.rejectValue("note", "sale_note_too_short", "not beş karakterden küçük olamaz");

		}

	}

}
