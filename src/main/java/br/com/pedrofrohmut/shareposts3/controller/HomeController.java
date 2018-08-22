package br.com.pedrofrohmut.shareposts3.controller;

import br.com.pedrofrohmut.shareposts3.util.RequestMappings;
import br.com.pedrofrohmut.shareposts3.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController
{
    @GetMapping(RequestMappings.HOME_INDEX)
    public String indexOnGet()
    {
        log.info(">>> INDEX METHOD CALLED!");
        return ViewNames.HOME_INDEX;
    }

    @GetMapping(RequestMappings.HOME_ABOUT)
    public String aboutOnGet()
    {
        log.info(">>> ABOUT METHOD CALLED!");
        return ViewNames.HOME_ABOUT;
    }
}
