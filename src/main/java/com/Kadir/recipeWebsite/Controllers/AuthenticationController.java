package com.Kadir.recipeWebsite.Controllers;

import com.Kadir.recipeWebsite.DTO.UserDto;
import com.Kadir.recipeWebsite.DTO.UserRequest;
import com.Kadir.recipeWebsite.DTO.UserResponse;
import com.Kadir.recipeWebsite.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final HttpServletResponse response;
    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }
    @PostMapping("/adminSave")
    public ResponseEntity<UserResponse> adminSave(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.adminSave(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest){
        UserResponse userResponse = authenticationService.auth(userRequest);
        setTokenCookie(userResponse.getToken());
        return ResponseEntity.ok(userResponse);
    }
    private void setTokenCookie(String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(86400); // Çerezin geçerlilik süresini ayarlayabilirsiniz (örnekte 1 gün)
        cookie.setPath("/"); // Çerezin tüm yollar için geçerli olmasını sağlar
        response.addCookie(cookie);
    }
}
