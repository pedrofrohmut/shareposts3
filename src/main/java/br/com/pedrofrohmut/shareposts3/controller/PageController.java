package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController
{
    @GetMapping(RequestMappings.PAGE_INDEX)
    public String index()
    {
        log.info(">>> INDEX METHOD CALLED!");
        return ViewNames.PAGE_INDEX;
    }

    @GetMapping(RequestMappings.PAGE_ABOUT)
    public String about()
    {
        log.info(">>> INDEX METHOD CALLED!");
        return ViewNames.PAGE_ABOUT;
    }
}
