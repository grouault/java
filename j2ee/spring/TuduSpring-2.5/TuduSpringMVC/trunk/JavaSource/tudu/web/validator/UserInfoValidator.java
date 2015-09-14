package tudu.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tudu.web.bean.UserInfoData;

public class UserInfoValidator implements Validator {

	public boolean supports(Class clazz) {
		return UserInfoData.class.isAssignableFrom(clazz);
	}

	public void validate(Object command, Errors errors) {
		UserInfoData data = (UserInfoData)command;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required", new Object[] {"password"}, "");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyPassword", "errors.required", new Object[] {"verifyPassword"}, "");
		if( !data.getPassword().equals(data.getVerifyPassword()) ) {
			errors.rejectValue("verifyPassword", "errors.required", new Object[] {"verifyPassword"}, "");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.required", new Object[] {"firstName"}, "");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.required", new Object[] {"lastName"}, "");

		if( errors.hasErrors() ) {
			errors.reject("register.info.1");
		}
	}

}
