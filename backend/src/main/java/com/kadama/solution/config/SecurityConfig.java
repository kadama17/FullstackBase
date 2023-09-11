package com.kadama.solution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.web.SecurityFilterChain;

import com.kadama.solution.service.UserService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	  @Bean
	    public UserDetailsService userDetailsService() {
		  	return new UserService();
	    }
	 
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     
	    	  http.csrf().disable()
              .authorizeHttpRequests((authorize) ->
                      //authorize.anyRequest().authenticated()
                      authorize.requestMatchers(HttpMethod.GET, "/**").permitAll()
                      .requestMatchers("/inscription", "/connexion", "/api/**").permitAll()               
                      .anyRequest().authenticated()
                      );

		 
	        return http.build();
	    }
	 

	 
}
