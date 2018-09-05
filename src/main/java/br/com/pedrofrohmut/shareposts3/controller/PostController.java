package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import br.com.pedrofrohmut.shareposts3.util.ModelAttributes;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.SessionAttributes;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PostController
{
    private final PostService postService;

    @Autowired
    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    // ## Mapping Methods ##
    @GetMapping(RequestMappings.POST_INDEX)
    public String indexOnGet(HttpSession session, Model model)
    {
        log.info(">>> POST INDEX ON GET METHOD CALLED!");

        User sessionLoggedInUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        log.info("    >> Session User Logged IN: " + sessionLoggedInUser);

        // TODO: load posts list + add to model
        List<Post> posts = postService.getPosts();

        if (posts != null) {
            model.addAttribute(ModelAttributes.POST_LIST_ALL, posts);
        } else {
            model.addAttribute(ModelAttributes.POST_LIST_ALL, new ArrayList<Post>());
        }

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
