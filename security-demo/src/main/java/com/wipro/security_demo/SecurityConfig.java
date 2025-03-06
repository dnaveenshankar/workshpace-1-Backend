package com.wipro.security_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	UserDetailsService appUserDetails() {
		
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		userDetailsManager.createUser(User.withUsername("naveen").password("{noop}naveen@123").roles("USER").build());
		userDetailsManager.createUser(User.withUsername("user").password("{noop}user@123").roles("USER").build());
		userDetailsManager.createUser(User.withUsername("raj").password("{noop}raj@123").roles("USER","ADMIN").build());
		
		return userDetailsManager;
	}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/user/**").hasRole("USER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/home").permitAll()
                .anyRequest().authenticated()
            )
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());

        return http.build();

    }
		
}
