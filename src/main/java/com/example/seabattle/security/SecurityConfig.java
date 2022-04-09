package com.example.seabattle.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(
            HttpMethod.POST,
            "/api/auth/register/passwordless",
            "/api/auth/register",
            "/api/auth/login",
            "/api/auth/token")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();

    http.csrf().disable();
  }
}
