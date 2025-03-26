package org.codenova.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String indexHandle() {
        return "index";
    }

    @GetMapping("/help")
    public String helpHandle() {
        return "help";
    }


}
