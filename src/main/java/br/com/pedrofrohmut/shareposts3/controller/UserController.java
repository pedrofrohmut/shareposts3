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

	@GetMapping(RequestMappings.USER_INDEX)
	public String indexOnGet()
	{
        log.info(">>> USER INDEX ON GET METHOD CALLED!");
		// TODO
		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_LOGIN)
	public String loginOnGet()
	{
        log.info(">>> USER LOGIN ON GET METHOD CALLED!");
		// TODO
		return ViewNames.USER_LOGIN;
	}

	@PostMapping(RequestMappings.USER_LOGIN)
	public String loginOnPost()
	{
        log.info(">>> USER LOGIN ON POST METHOD CALLED!");
		// TODO
		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_REGISTER)
	public String registerOnGet(Model model)
	{
		log.info(">>> USER REGISTER ON GET METHOD CALLED!");
		model.addAttribute("user", new User());
		return ViewNames.USER_REGISTER;
	}
	
	@PostMapping(RequestMappings.USER_REGISTER)
	public String registerOnPost(@Valid @ModelAttribute User user, BindingResult result, Model model)
	{
		log.info(">>> USER REGISTER ON POST METHOD CALLED!");
		log.info("User - ModelAttribute :: " + user);

		if (result.hasErrors()) {
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
			return ViewNames.USER_LOGIN;
		} else {
            model.addAttribute("message", "Error: User was not registered");
			return ViewNames.DEV_FAILURE;
		}
	}

	@PostMapping(RequestMappings.USER_LOGOUT)
	public String logoutOnPost()
	{
        log.info(">>> USER LOGOUT ON POST METHOD CALLED!");
		// TODO
		return RequestMappings.REDIRECT_HOME_INDEX;
	}
}
