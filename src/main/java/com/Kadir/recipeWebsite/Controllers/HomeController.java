package com.Kadir.recipeWebsite.Controllers;

import com.Kadir.recipeWebsite.Dto.LoginRequest;
import com.Kadir.recipeWebsite.Enums.Role;
import com.Kadir.recipeWebsite.Models.User;
import com.Kadir.recipeWebsite.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @RequestMapping("/index")
    public String home() {
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
    @GetMapping("/admin/login")
    public String adminLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "adminLogin";
    }
    @GetMapping("/recipe")
    public String recipe(){
        if(LoginController.UserId != null) return "recipe";
        else return "redirect:/login";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/admin/dashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @RequestMapping("/user/dashboard")
    public String userDashboard() {
        return "userDashboard";
    }
}
