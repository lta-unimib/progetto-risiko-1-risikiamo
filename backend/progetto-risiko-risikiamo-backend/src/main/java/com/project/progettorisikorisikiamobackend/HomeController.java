package com.project.progettorisikorisikiamobackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    // simple code to test
    @RequestMapping("/test")
    public @ResponseBody String testEndpoint() {
        return test();
    }

    // funzione test per quality gate
    public String test() {
        return "test";
    }

}