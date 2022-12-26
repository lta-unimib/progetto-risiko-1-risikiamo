package com.project.progettorisikorisikiamobackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    // simple code to test get request
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody String testEndpoString() {
        return test("test");
    }

    // funzione test per quality gate
    public String test(String test) {
        return test;
    }

}