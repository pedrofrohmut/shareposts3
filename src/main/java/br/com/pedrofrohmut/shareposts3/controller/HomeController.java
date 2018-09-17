package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.util.ModelAttributes;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.SessionAttributes;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController
{
    @GetMapping(RequestMappings.HOME_INDEX)
    public String indexOnGet(
            HttpSession session,
            @RequestParam(name = ModelAttributes.MESSAGE, required = false) String message,
            @RequestParam(name = ModelAttributes.ERROR_MESSAGE, required = false) String errorMessage,
            Model model
        )
    {
        log.info(">>> HOME_INDEX METHOD CALLED!");

        User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        log.info("  >> Session User Logged In: " + sessionUser);

        if (sessionUser != null && sessionUser.getId() >= 1) {
            return RequestMappings.REDIRECT_POST_INDEX;
        } else {
            model.addAttribute(ModelAttributes.MESSAGE, message);
            model.addAttribute(ModelAttributes.ERROR_MESSAGE, errorMessage);
            return ViewNames.HOME_INDEX;
        }
    }

    @GetMapping(RequestMappings.HOME_ABOUT)
    public String aboutOnGet()
    {
        log.info(">>> HOME_ABOUT METHOD CALLED!");
        return ViewNames.HOME_ABOUT;
    }
}
