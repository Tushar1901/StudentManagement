package com.StdMngmnt.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/login", "/css/**", "/students/view","/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/students/add", "/students/save", "/fees", "/users/add", "/users/save","/students/update/**","/students/delete/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()) // 🚨 TEMPORARY: Disable CSRF (2️⃣ CSRF Protection → Spring Security blocks POST requests without a CSRF token)
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureHandler(customAuthenticationFailureHandler()) // Use custom handler
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                org.springframework.security.core.AuthenticationException exception)
                    throws IOException, ServletException {
                request.getSession().setAttribute("error", "Invalid Credentials"); // Set error message in session
                response.sendRedirect("/auth/login?error=true"); // Redirect back to login page
            }
        };
    }
}
