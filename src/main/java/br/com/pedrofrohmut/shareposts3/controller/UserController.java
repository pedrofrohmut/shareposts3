package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import br.com.pedrofrohmut.shareposts3.util.AttributeNames;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import br.com.pedrofrohmut.shareposts3.validation.user.LoginForm;
import br.com.pedrofrohmut.shareposts3.validation.user.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_LOGIN)
	public String loginOnGet(
	        @RequestParam(name = AttributeNames.MESSAGE, required = false) String message, Model model)
	{
        log.info(">>> USER LOGIN ON GET METHOD CALLED!");
		model.addAttribute(AttributeNames.USER_OBJ, new User());
		model.addAttribute(AttributeNames.MESSAGE, message);
		return ViewNames.USER_LOGIN;
	}

	@PostMapping(RequestMappings.USER_LOGIN)
	public String loginOnPost(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model)
	{
        log.info(">>> USER LOGIN ON POST METHOD CALLED!");

		if (result.hasErrors()) {
		    for (FieldError field : result.getFieldErrors()) {
		        log.error("    Field Error: " + field.getDefaultMessage());
            }

		    return ViewNames.USER_LOGIN;
        }

        User authUser = userService.findUserByEmail(loginForm.getEmail());

		if (authUser == null) {
		    model.addAttribute(AttributeNames.ERROR_MESSAGE, "No user with the provided e-mail was found.");
		    return ViewNames.USER_LOGIN;
        }

        if (authUser.getPassword().equals(loginForm.getPassword())) {
            model.addAttribute(AttributeNames.ERROR_MESSAGE, "Wrong password, try again.");
            return ViewNames.USER_LOGIN;
        }

		return RequestMappings.REDIRECT_POST_INDEX;
	}

	@GetMapping(RequestMappings.USER_REGISTER)
	public String registerOnGet(Model model)
	{
		log.info(">>> USER REGISTER ON GET METHOD CALLED!");
		model.addAttribute(AttributeNames.USER_OBJ, new RegisterForm());
		return ViewNames.USER_REGISTER;
	}
	
	@PostMapping(RequestMappings.USER_REGISTER)
	public String registerOnPost(@Valid @ModelAttribute RegisterForm registerForm, BindingResult result,
                                 RedirectAttributes redirectAttr, Model model)
	{
		log.info(">>> USER REGISTER ON POST METHOD CALLED!");
		log.info("User - ModelAttribute :: " + registerForm);

		if (result.hasErrors()) {
			return ViewNames.USER_REGISTER;
        }

        User user = new User();
		user.setName(registerForm.getName());
		user.setEmail(registerForm.getEmail());
		user.setPassword(registerForm.getPassword());

		boolean successfulOperation = userService.create(user);
		
		if (successfulOperation) {
            // TODO: value pulled from a messages file + i18n
            redirectAttr.addAttribute(AttributeNames.MESSAGE, "User successfully registered");
			return RequestMappings.REDIRECT_USER_LOGIN;
		} else {
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
