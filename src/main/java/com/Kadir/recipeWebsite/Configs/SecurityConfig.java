package com.Kadir.recipeWebsite.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor


public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/index").permitAll()
                .mvcMatchers("/loginPage").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(cookieTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Yeni satır: JwtAuthenticationFilter'ı ekleyin
                .headers().frameOptions().disable()
                .and()
                .rememberMe()
                .key("uniqueAndSecretKey")
                .tokenValiditySeconds(86400);
    }

    public CookieTokenAuthenticationFilter cookieTokenAuthenticationFilter() throws Exception {
        CookieTokenAuthenticationFilter filter = new CookieTokenAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Burada AuthenticationManager'ı yapılandırın ve oluşturun
        auth.authenticationProvider(authenticationProvider);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        // H2 konsolu için WebSecurity yapılandırması
        web.ignoring().antMatchers("/h2-console/**");
    }
    */
}