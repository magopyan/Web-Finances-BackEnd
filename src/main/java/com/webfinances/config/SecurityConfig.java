package com.webfinances.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setExposedHeaders(List.of("Content-Disposition"));

        http.csrf().disable();
        http.cors().configurationSource(request -> corsConfiguration);
        http.authorizeRequests().anyRequest().permitAll();
        http.oauth2ResourceServer()
                .jwt();

//        http.csrf().disable();
//        http.cors();
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();
//        http.oauth2ResourceServer()
//                .jwt();
    }
}
