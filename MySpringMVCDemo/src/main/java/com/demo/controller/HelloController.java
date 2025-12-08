package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "hello"; // or "welcome" or "index" depending on which JSP you want
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/welcome")
    public ModelAndView sayWelcome() {
        String msg = "This is welcome message from controller";
        return new ModelAndView("welcome", "message", msg);
    }

    @GetMapping("/testdata")
    public String testdata(Model model) {
        model.addAttribute("name", "Dikshita");
        model.addAttribute("email", "dikshussingh@gmail.com");
        return "testdata";
    }
}

