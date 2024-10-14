package com.florescon.floresdm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.florescon.floresdm.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        System.out.println("CUSTOM EN SECUR");
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SECUR FOLTER CHAIN");
        http
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/").permitAll() // Páginas públicas    
            .requestMatchers("/login").permitAll() // Páginas públicas
            .requestMatchers("/index").permitAll() // Páginas públicas
            .requestMatchers("../static/styles.css").permitAll() // Páginas públicas
            .requestMatchers("/css/**").permitAll() // Páginas públicas
            .requestMatchers("/register").permitAll() // Páginas públicas
            .anyRequest().authenticated() // Cualquier otra solicitud debe estar autenticada
            )

            //.formLogin(form -> form
            //   .loginPage("/login").permitAll() // Página personalizada de login
            //    .defaultSuccessUrl("/index.html", true) // Redirigir a la página de éxito tras login
            //)

            .formLogin(form -> form
                .permitAll() // Usar la página de login predeterminada
                .defaultSuccessUrl("/", true) // Redirigir a la página de éxito tras login
            )


            .logout(config -> config.logoutSuccessUrl("/")); // Página después de hacer logout
            

        return http.build();
    }

    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        System.out.println("DAO SECUR");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
