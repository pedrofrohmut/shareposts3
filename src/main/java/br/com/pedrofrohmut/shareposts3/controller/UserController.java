package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
	public String registerOnGet(Model model)
	{
		log.info(">>> USER REGISTER METHOD CALLED!");
		model.addAttribute("user", new User());
		return ViewNames.USER_REGISTER;
	}
	
	@PostMapping(RequestMappings.USER_REGISTER)
	public String registerOnPost(
	        @Valid @ModelAttribute User user,
			BindingResult result,
			Model model
		)
	{
		log.info(">>> USER REGISTER METHOD CALLED!");
		log.info("User - ModelAttribute :: " + user);

		if (result.hasErrors()) {

		    log.info(""); log.info("### ERRORS ###");
            for (FieldError f : result.getFieldErrors()) {
                log.error("    Field Error: " + f.getDefaultMessage());
            }
            log.info("### ERRORS ###"); log.info("");

            log.info(""); log.info("### ERRORS - GLOBAL ###");
            for (ObjectError objErr : result.getGlobalErrors()) {
                log.error("    Field Error: " + objErr.getDefaultMessage());
            }
            log.info("### ERRORS - GLOBAL ###"); log.info("");

            return ViewNames.USER_REGISTER;
        }
		
		// Trim Strings
		user.setName( user.getName().trim() );
		user.setEmail( user.getEmail().trim() );
		user.setConfirmEmail( user.getConfirmEmail() );
		user.setPassword( user.getPassword().trim() );
		user.setConfirmPassword( user.getConfirmPassword().trim() );


		boolean successfulOperation = userService.create(user);
		
		if (successfulOperation) {
            // TODO: value pulled from a messages file + i18n
            // TODO: make it a Flash Message and redirect to login page
		    model.addAttribute("message", "User successfully registered");
			return ViewNames.USER_REGISTER;
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
