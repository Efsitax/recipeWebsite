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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public static Long UserId;
    @PostMapping("/login-process")
    public String login(@ModelAttribute("loginRequest") @Valid LoginRequest loginRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Hata durumunda, login.html sayfasına geri dön ve hata mesajlarını göster
            return "login";
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Kullanıcı adı ve şifre kontrolü
        // Örneğin, UserRepository kullanarak kullanıcıyı veritabanında arayabilirsiniz
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            // Hatalı kullanıcı adı veya şifre
            model.addAttribute("error", "Invalid username or password");
            return "login";
        } else if (user.getRoles().contains(Role.ADMIN)) {
            UserId= user.getId();
            return "redirect:/admin/dashboard";
        } else {
            UserId= user.getId();
            return "redirect:/user/dashboard";
        }

    }
    @PostMapping("/admin/login-process")
    public String adminLogin(@ModelAttribute("loginRequest") @Valid LoginRequest loginRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Hata durumunda, login.html sayfasına geri dön ve hata mesajlarını göster
            return "adminLogin";
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Kullanıcı adı ve şifre kontrolü
        // Örneğin, UserRepository kullanarak kullanıcıyı veritabanında arayabilirsiniz
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            // Hatalı kullanıcı adı veya şifre
            model.addAttribute("error", "Invalid username or password");
            return "adminLogin";
        }
        else if(!user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("error", "You're not authorized.");
            return "adminLogin";
        }

        // Başarılı giriş durumunda, kullanıcıyı yönlendir
        return "redirect:/admin/dashboard";
    }
}
