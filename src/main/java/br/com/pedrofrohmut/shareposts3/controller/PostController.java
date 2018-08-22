package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class PostController
{
    @GetMapping(RequestMappings.POST_INDEX)
    public String indexOnGet()
    {
        log.info(">>> POST INDEX ON GET METHOD CALLED!");
        // TODO
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
