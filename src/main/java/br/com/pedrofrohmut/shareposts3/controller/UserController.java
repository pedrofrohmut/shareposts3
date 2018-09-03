package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import br.com.pedrofrohmut.shareposts3.util.AttributeNames;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import br.com.pedrofrohmut.shareposts3.validation.user.UserLoginForm;
import br.com.pedrofrohmut.shareposts3.validation.user.UserRegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class UserController 
{
	// ## Fields ##
	private final UserService userService;

	// ## Constructor ##
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	// ## Mapping Methods ##
	@GetMapping(RequestMappings.USER_INDEX)
	public String indexOnGet()
	{
        log.info(">>> USER INDEX ON GET METHOD CALLED!");
		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_LOGIN)
	public String loginOnGet(
	        @RequestParam(name = AttributeNames.MESSAGE, required = false) String message, Model model)
	{
        log.info(">>> USER LOGIN ON GET METHOD CALLED!");
		model.addAttribute(AttributeNames.USER_LOGIN_FORM, new UserLoginForm());
		model.addAttribute(AttributeNames.MESSAGE, message);
		return ViewNames.USER_LOGIN;
	}

	@PostMapping(RequestMappings.USER_LOGIN)
	public String loginOnPost(
			final @Valid @ModelAttribute UserLoginForm userLoginForm,
			final BindingResult result,
			final Model model,
			final HttpSession session
		)
	{
        log.info(">>> USER LOGIN ON POST METHOD CALLED!");

		if (result.hasErrors()) {
            log.error("\n\n    >>  HAS ERRORS ON USER LOGIN FORM << \n\n");
		    for (FieldError field : result.getFieldErrors()) {
		        log.error("    Field Error: " + field.getDefaultMessage());
            }
            log.error("\n\n");

		    return ViewNames.USER_LOGIN;
        }

        User authUser = userService.findUserByEmail(userLoginForm.getEmail());

		if (authUser == null) {
			// TODO: message value to messages file + i18n
		    model.addAttribute(AttributeNames.ERROR_MESSAGE, "No user with the provided e-mail was found.");
		    return ViewNames.USER_LOGIN;
        }

        if ( !authUser.getPassword().equals(userLoginForm.getPassword()) ) {
			// TODO: message value to messages file + i18n
            model.addAttribute(AttributeNames.ERROR_MESSAGE, "Wrong password, try again.");
            return ViewNames.USER_LOGIN;
        }

        log.info("    >> Auth User: " + authUser);
		session.setAttribute(AttributeNames.SESSION_USER_LOGGED_IN, authUser);

		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_REGISTER)
	public String registerOnGet(Model model)
	{
		log.info(">>> USER REGISTER ON GET METHOD CALLED!");
		model.addAttribute(AttributeNames.USER_REGISTER_FORM, new UserRegisterForm());
		return ViewNames.USER_REGISTER;
	}
	
	@PostMapping(RequestMappings.USER_REGISTER)
	public String registerOnPost(
	        final @Valid @ModelAttribute UserRegisterForm userRegisterForm,
            final BindingResult result,
            final Model model,
            final RedirectAttributes redirectAttributes
        )
	{
		log.info(">>> USER REGISTER ON POST METHOD CALLED!");
		log.info("User - ModelAttribute :: " + userRegisterForm);

		if (result.hasErrors()) {
		    log.error("\n\n    >>  HAS ERRORS ON USER REGISTER FORM << \n\n");
		    for (FieldError f : result.getFieldErrors()) {
		        log.error("    > field error: " + f.getDefaultMessage());
            }
		    log.error("\n\n");

			return ViewNames.USER_REGISTER;
        }

        User user = new User();
		user.setName(userRegisterForm.getName());
		user.setEmail(userRegisterForm.getEmail());
		user.setPassword(userRegisterForm.getPassword());

		boolean successfulOperation = userService.create(user);
		
		if (successfulOperation) {
            // TODO: message value to messages file + i18n
            redirectAttributes.addAttribute(AttributeNames.MESSAGE, "User successfully registered");
			return RequestMappings.REDIRECT_USER_LOGIN;
		} else {
			// TODO: message value to messages file + i18n
            model.addAttribute(AttributeNames.MESSAGE, "Error: User was not registered");
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
