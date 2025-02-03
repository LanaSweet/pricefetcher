package com.store.price_fetcher.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .requiresChannel(channel -> channel
	                .anyRequest()
	                .requiresSecure()
	            )
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/h2-console/**").permitAll()
	                .anyRequest().permitAll()
	            )
	            .csrf().disable() // Disable CSRF protection for the H2 console
	            .headers().frameOptions().disable(); // Allow frames to display the H2 console
	        return http.build();
	    }
}
