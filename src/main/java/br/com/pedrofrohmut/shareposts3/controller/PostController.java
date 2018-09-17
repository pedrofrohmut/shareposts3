package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import br.com.pedrofrohmut.shareposts3.util.ModelAttributes;
import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.SessionAttributes;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import br.com.pedrofrohmut.shareposts3.validation.user.post.PostAddForm;
import br.com.pedrofrohmut.shareposts3.validation.user.post.PostDeleteForm;
import br.com.pedrofrohmut.shareposts3.validation.user.post.PostEditForm;
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
    public String indexOnGet(
            @RequestParam(name = ModelAttributes.ERROR_MESSAGE, required = false) String errorMessage,
            final HttpSession session,
            final RedirectAttributes redirectAttributes,
            final Model model
        )
    {
        log.info(">>> POST INDEX ON GET METHOD CALLED!");

        if (errorMessage != null && !errorMessage.equals("")) {
            log.info("    >> On Post Index, Error Message = " + errorMessage);
            model.addAttribute(ModelAttributes.ERROR_MESSAGE, errorMessage);
        }

        // Get current logged in user
        User sessionLoggedInUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        log.info("    >> Session User Logged IN: " + sessionLoggedInUser);

        if (sessionLoggedInUser == null || sessionLoggedInUser.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE, errorMessage);
            return RequestMappings.REDIRECT_HOME_INDEX;
        } else {
            // Load Posts to the View
            List<Post> posts = postService.getPosts();
            model.addAttribute(ModelAttributes.POST_LIST_ALL, posts);

            return ViewNames.POST_INDEX;
        }
    }

    @GetMapping(RequestMappings.POST_ADD)
    public String addOnGet(HttpSession session, RedirectAttributes redirectAttributes, Model model)
    {
        log.info(">>> POST ADD ON GET METHOD CALLED!");

        User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);

        if (sessionUser == null || sessionUser.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.MESSAGE,
                    "No user is logged in the current session. Please log in to add a new post.");
            return RequestMappings.REDIRECT_HOME_INDEX;
        } else {
            PostAddForm postAddForm = new PostAddForm();
            postAddForm.setUserId(sessionUser.getId());
            model.addAttribute(ModelAttributes.POST_ADD_FORM, postAddForm);

            return ViewNames.POST_ADD;
        }
    }

    @PostMapping(RequestMappings.POST_ADD)
    public String addOnPost(final @Valid @ModelAttribute PostAddForm postAddForm, final BindingResult result)
    {
        log.info(">>> POST ADD ON POST METHOD CALLED!");

        log.info("    >> post add form: " + postAddForm);

        if (result.hasErrors()) {
            log.error("\n\n    >>  HAS ERRORS ON POST ADD FORM << \n\n");
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

            final boolean postCreated = postService.create(post); // TODO: handle the boolean result

            return RequestMappings.REDIRECT_POST_INDEX;
        }
    }

    @GetMapping(RequestMappings.POST_SHOW + "/{id}")
    public String showOnGet(
            final @PathVariable int id,
            final HttpSession session,
            final RedirectAttributes redirectAttributes,
            final Model model
        )
    {
        log.info(">>> POST SHOW ON GET METHOD CALLED!");

        User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        if (sessionUser == null || sessionUser.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No user is logged in the current session. Please log in to see this post details.");
            return RequestMappings.REDIRECT_HOME_INDEX;
        }

        final Post post = postService.findPostById(id);

        if (post == null || post.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No post with the id passed was found.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else {
            model.addAttribute(ModelAttributes.POST_SHOW_FORM, post);
            model.addAttribute(ModelAttributes.POST_DELETE_FORM, post);
            return ViewNames.POST_SHOW;
        }
    }

    @GetMapping(RequestMappings.POST_EDIT + "/{id}")
    public String editOnGet(
            final @PathVariable int id,
            final HttpSession session,
            final RedirectAttributes redirectAttributes,
            final Model model
        )
    {
        log.info(">>> POST EDIT ON GET METHOD CALLED!");

        User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);

        final Post post = postService.findPostById(id);

        if (sessionUser == null || sessionUser.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No user is logged in the current session. Please log in to edit this post.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else if (post == null || post.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No post with the id passed was found.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else if (post.getUser().getId() != sessionUser.getId()) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "The current user is not the owner of this post and cannot edit it.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else {
            PostEditForm postEditForm = new PostEditForm();

            postEditForm.setId(post.getId());
            postEditForm.setTitle(post.getTitle());
            postEditForm.setBody(post.getBody());
            postEditForm.setUserId(post.getUser().getId());

            model.addAttribute(ModelAttributes.POST_EDIT_FORM, postEditForm);
            return ViewNames.POST_EDIT;
        }
    }

    @PostMapping(RequestMappings.POST_EDIT)
    public String editOnPost(
            final @Valid @ModelAttribute(ModelAttributes.POST_EDIT_FORM) PostEditForm postEditForm,
            final BindingResult result
        )
    {
        log.info(">>> POST EDIT ON POST METHOD CALLED!");

        if (result.hasErrors()) {
            log.error("  >> Post Edit Form - With Errors: " + postEditForm);
            log.error("\n\n    >>  HAS ERRORS ON POST EDIT FORM << \n\n");
            for (FieldError f : result.getFieldErrors()) {
                log.error("    > field error: " + f.getDefaultMessage());
            }
            log.error("\n\n");

            return ViewNames.POST_EDIT;
        } else {
            Post post = new Post();

            post.setId(postEditForm.getId());
            post.setTitle(postEditForm.getTitle());
            post.setBody(postEditForm.getBody());

            User user = new User();
            user.setId(postEditForm.getUserId());
            post.setUser(user);

            final boolean postUpdated = postService.update(post); // TODO: handle the boolean result

            return RequestMappings.REDIRECT_POST_INDEX;
        }
    }

    @PostMapping(RequestMappings.POST_DELETE)
    public String deleteOnPost(
            final @Valid @ModelAttribute(ModelAttributes.POST_DELETE_FORM) PostDeleteForm postDeleteForm,
            final BindingResult result,
            final HttpSession session,
            final RedirectAttributes redirectAttributes
        )
    {
        log.info(">>> POST DELETE ON POST METHOD CALLED!");

        if (result.hasErrors()) {
            log.info("  Errors On >>> POST DELETE FORM");
            return ViewNames.POST_SHOW;
        }

        final int id = postDeleteForm.getId();

        final User sessionUser = (User) session.getAttribute(SessionAttributes.SESSION_USER_LOGGED_IN);
        final Post post = postService.findPostById(id);


        if (sessionUser == null || sessionUser.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No user is logged in the current session. Please log in to delete this post.");
            return RequestMappings.REDIRECT_HOME_INDEX;
        } else if (post == null || post.getId() < 1) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "No post with the id passed was found.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else if (sessionUser.getId() != post.getUser().getId()) {
            redirectAttributes.addAttribute(ModelAttributes.ERROR_MESSAGE,
                    "The current user is not the owner of this post and cannot delete it.");
            return RequestMappings.REDIRECT_POST_INDEX;
        } else {
            boolean postDeleted = postService.delete(id); // TODO: handle the boolean result

            redirectAttributes.addAttribute(ModelAttributes.MESSAGE, "post deleted.");
            return RequestMappings.REDIRECT_POST_INDEX;
        }
    }
}
