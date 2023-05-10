package com.Kadir.recipeWebsite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(){
        return "login";
    }

    @GetMapping("/registerPage")
    public String register(){
        return "register";
    }
}
