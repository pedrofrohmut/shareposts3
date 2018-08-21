package br.com.pedrofrohmut.shareposts3.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
=======
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 6d021d41efb477a267fdd500b7e847fcd2c79553
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< HEAD
import br.com.pedrofrohmut.shareposts3.service.UserService;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;

=======
>>>>>>> 6d021d41efb477a267fdd500b7e847fcd2c79553
@Slf4j
@Controller
public class UserController 
{
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(RequestMappings.USER_REGISTER)
	public String registerOnGet()
	{
		log.info(">>> USER REGISTER METHOD CALLED!");
		return ViewNames.USER_REGISTER;
	}
	
	@PostMapping(RequestMappings.USER_REGISTER)
	public String registerOnPost(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String confirmPassword,
			Model model
		)
	{
		log.info(">>> USER REGISTER METHOD CALLED!");
		log.info("User :: name = " + name + ", email = " + email + ", pass = " + 
				password + ", confirmPassword = " + confirmPassword);
		
		// Trim Strings
		name = name.trim();
		email = email.trim();
		password = password.trim();
		confirmPassword = confirmPassword.trim();
		
		// initialize errors
		String nameErr = "";
		String emailErr = "";
		String passwordErr = "";
		String confirmPasswordErr = "";
		
		// Validate name 
		if (name.isEmpty()) {
			// add nameErr
			nameErr = "Name is required and cannot be empty";
		}
		
		// Validate email
		if (email.isEmpty()) {
			// add emailErr
			email = "E-mail is required and cannot be empty";
		}
		
		// Validate password
		if (password.isEmpty()) {
			// add passwordErr
			passwordErr = "Password is required and cannot be empty"; 
		} else 
			if (password.length() < 6) {
				// add passwordErr
				passwordErr = "Password must be at least 6 characters long";
			}
		
		// Validate confirm password
		if (confirmPassword.isEmpty()) {
			// add confirmPasswordErr
			confirmPassword = "Confirm Password is required and must match the informed";
		} else 
			if ( !confirmPassword.equals(password) ) {
				// add confirmPasswordErr
				confirmPassword = "Confirm Password did not match the provided";
			}
		
		if (
		       !nameErr.isEmpty() ||
		       !emailErr.isEmpty() ||
		       !passwordErr.isEmpty() ||
		       !confirmPasswordErr.isEmpty()
		   )
		{
			model.addAttribute("nameErr", nameErr);
			model.addAttribute("emailErr", emailErr);
			model.addAttribute("passwordErr", passwordErr);
			model.addAttribute("confirmPasswordErr", confirmPasswordErr);
			
			return ViewNames.USER_REGISTER;
		}
			
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		boolean successfulOperation = userService.create(user);
		
		if (successfulOperation) {
			return ViewNames.DEV_SUCCESS;			
		} else {
			return ViewNames.DEV_FAILURE;
		}
	}
	
	@GetMapping("user/login")
	public String loginOnGet() // TODO: implement it
	{
		log.info(">>> USER LOGIN METHOD CALLED!");
		return "user/login";
	}
}
