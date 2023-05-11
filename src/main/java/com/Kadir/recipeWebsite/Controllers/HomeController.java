package com.Kadir.recipeWebsite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

}
