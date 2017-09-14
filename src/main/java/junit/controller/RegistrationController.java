package junit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import junit.entity.User;
import junit.service.RegistrationService;
import junit.util.Constants;
import junit.validator.UserValidator;

/**
 * @author GovindSingh
 */
@Controller
public class RegistrationController {
	@Autowired
	@Qualifier("registrationService")
	private RegistrationService registrationService;

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	/**
	 * Register user profile with given details
	 * 
	 * @param user User object with all required fields
	 * @return success if registration is successful else return error message
	 */
	@RequestMapping(value = "register.htm", method = RequestMethod.POST)
	public @ResponseBody String registerUser(@ModelAttribute("User") User user) {
		String userValidationResult = UserValidator.validateUser(user);

		if (!userValidationResult.equals(Constants.SUCCESS)) {
			return userValidationResult;
		}

		return registrationService.registerUser(user);
	}
}
