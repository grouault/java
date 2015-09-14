package tudu.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tudu.web.bean.RecoverPasswordData;

public class RecoverPasswordValidator implements Validator {

	public boolean supports(Class clazz) {
		System.out.println("clazz = "+clazz);
		return RecoverPasswordData.class.isAssignableFrom(clazz);
	}

	public void validate(Object command, Errors errors) {
		RecoverPasswordData data = (RecoverPasswordData)command;
		System.out.println("data.getLogin() = "+data.getLogin());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "errors.required", new Object[] {"login"}, "");

		if( errors.hasErrors() ) {
			errors.reject("register.info.1");
		}
	}

}
