package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.util.AttributeNames;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@SessionAttributes(AttributeNames.SESSION_USER_LOGGED_IN)
public class PostController
{
    // ## Mapping Methods ##
    @GetMapping(RequestMappings.POST_INDEX)
    public String indexOnGet(HttpSession session)
    {
        log.info(">>> POST INDEX ON GET METHOD CALLED!");

        User sessionLoggedInUser = (User) session.getAttribute(AttributeNames.SESSION_USER_LOGGED_IN);
        log.info("    >> Session User Logged IN: " + sessionLoggedInUser);

        // TODO: load posts list + add to model

        return ViewNames.POST_INDEX;
    }

    @GetMapping(RequestMappings.POST_ADD)
    public String addOnGet()
    {
        log.info(">>> POST ADD ON GET METHOD CALLED!");
        // TODO
        return ViewNames.POST_ADD;
    }

    @PostMapping(RequestMappings.POST_ADD)
    public String addOnPost()
    {
        log.info(">>> POST ADD ON POST METHOD CALLED!");
        // TODO
        return RequestMappings.REDIRECT_POST_INDEX;
    }

    @GetMapping(RequestMappings.POST_SHOW)
    public String showOnGet(@PathVariable int id)
    {
        log.info(">>> POST SHOW ON GET METHOD CALLED!");
        // TODO
        return ViewNames.POST_SHOW;
    }

    @GetMapping(RequestMappings.POST_EDIT)
    public String editOnGet(@PathVariable int id)
    {
        log.info(">>> POST EDIT ON GET METHOD CALLED!");
        // TODO
        return ViewNames.POST_EDIT;
    }

    @PostMapping(RequestMappings.POST_EDIT)
    public String editOnPost()
    {
        log.info(">>> POST EDIT ON POST METHOD CALLED!");
        // TODO
        return RequestMappings.REDIRECT_POST_INDEX;
    }

    @PostMapping(RequestMappings.POST_DELETE)
    public String deleteOnPost(@RequestParam int id)
    {
        log.info(">>> POST DELETE ON POST METHOD CALLED!");
        // TODO
        return RequestMappings.REDIRECT_POST_INDEX;
    }
}
