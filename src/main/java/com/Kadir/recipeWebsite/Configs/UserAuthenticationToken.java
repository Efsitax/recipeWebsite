package com.Kadir.recipeWebsite.Configs;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private final String role;

    public UserAuthenticationToken(String token, String role) {
        super(null);
        this.token = token;
        this.role = role;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}

