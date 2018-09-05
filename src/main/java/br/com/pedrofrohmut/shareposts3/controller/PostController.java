package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import br.com.pedrofrohmut.shareposts3.util.ModelAttributes;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.SessionAttributes;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import br.com.pedrofrohmut.shareposts3.validation.user.PostAddForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

//TODO: Control Access in all methods
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
    public String indexOnGet(final HttpSession session, final Model model)
    {
        log.info(">>> POST INDEX ON GET METHOD CALLED!");

        // Get current logged in user
        User sessionLoggedInUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        log.info("    >> Session User Logged IN: " + sessionLoggedInUser);

        if (sessionLoggedInUser == null || sessionLoggedInUser.getId() < 1) {
            return RequestMappings.REDIRECT_HOME_INDEX;
        } else {
            // Load Posts to the View
            List<Post> posts = postService.getPosts();
            model.addAttribute(ModelAttributes.POST_LIST_ALL, posts);

            return ViewNames.POST_INDEX;
        }
    }

    @GetMapping(RequestMappings.POST_ADD)
    public String addOnGet(HttpSession session, Model model)
    {
        log.info(">>> POST ADD ON GET METHOD CALLED!");

        User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        // TODO: redirect on User Not Logged In
        PostAddForm postAddForm = new PostAddForm();
        postAddForm.setUserId(sessionUser.getId());
        model.addAttribute(ModelAttributes.POST_ADD_FORM, postAddForm);

        return ViewNames.POST_ADD;
    }

    @PostMapping(RequestMappings.POST_ADD)
    public String addOnPost(final @Valid @ModelAttribute PostAddForm postAddForm, final BindingResult result)
    {
        log.info(">>> POST ADD ON POST METHOD CALLED!");

        log.info("    >> post add form: " + postAddForm);

        if (result.hasErrors()) {
            log.error("\n\n    >>  HAS ERRORS ON USER REGISTER FORM << \n\n");
            for (FieldError f : result.getFieldErrors()) {
                log.error("    > field error: " + f.getDefaultMessage());
            }
            log.error("\n\n");

            return ViewNames.POST_ADD;
        } else {
            User user = new User();
            user.setId(postAddForm.getUserId());

            Post post = new Post();
            post.setTitle(postAddForm.getTitle());
            post.setBody(postAddForm.getBody());
            post.setUser(user);

            postService.create(post);

            return RequestMappings.REDIRECT_POST_INDEX;
        }
    }

    @GetMapping(RequestMappings.POST_SHOW + "/{id}")
    public String showOnGet(@PathVariable int id)
    {
        log.info(">>> POST SHOW ON GET METHOD CALLED!");
        // TODO
        return ViewNames.POST_SHOW;
    }

    @GetMapping(RequestMappings.POST_EDIT + "/{id}")
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
