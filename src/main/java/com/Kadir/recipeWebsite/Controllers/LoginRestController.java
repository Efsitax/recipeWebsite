package com.Kadir.recipeWebsite.Controllers;

import com.Kadir.recipeWebsite.Dto.LoginRequest;
import com.Kadir.recipeWebsite.Dto.RegistrationRequest;
import com.Kadir.recipeWebsite.Enums.Role;
import com.Kadir.recipeWebsite.Models.User;
import com.Kadir.recipeWebsite.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login-processs")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                    User user = userRepository.findByUsername(loginRequest.getUsername());
                    LoginController.UserId = user.getId();
        } catch (BadCredentialsException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; // Çıkış yapıldıktan sonra yönlendirilecek sayfa
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Kullanıcı bilgilerini al
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        String name = registrationRequest.getName();
        String surname = registrationRequest.getSurname();
        // Diğer gerekli bilgileri alabilirsiniz

        // Kullanıcıyı oluştur
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRoles(List.of(Role.USER));
        // Diğer kullanıcı bilgilerini ayarlayabilirsiniz

        // Kullanıcıyı kaydet
        userRepository.save(user);

        return ResponseEntity.ok("Kullanıcı kaydı başarılı");
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegistrationRequest registrationRequest) {
        // Kullanıcı bilgilerini al
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        String name = registrationRequest.getName();
        String surname = registrationRequest.getSurname();
        // Diğer gerekli bilgileri alabilirsiniz

        // Kullanıcıyı oluştur
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRoles(List.of(Role.USER, Role.ADMIN));
        // Diğer kullanıcı bilgilerini ayarlayabilirsiniz

        // Kullanıcıyı kaydet
        userRepository.save(user);

        return ResponseEntity.ok("Kullanıcı kaydı başarılı");
    }
    private String generateSessionIdentifier() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


}
