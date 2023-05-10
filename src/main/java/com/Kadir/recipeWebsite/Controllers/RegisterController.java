package com.Kadir.recipeWebsite.Controllers;
import com.Kadir.recipeWebsite.Models.User;
import com.Kadir.recipeWebsite.Services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kullanıcı adı zaten kullanılıyor");
        }

        // Yeni kullanıcı oluştur
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        userService.add(user);

        return ResponseEntity.ok("Kayıt başarılı");
    }
}
