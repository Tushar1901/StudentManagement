package com.StdMngmnt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","/auth/login","/css/**","/fees")
                        .requestMatchers("/","/auth/login","/css/**","/students/view").permitAll()  // ✅ Public pages
                        .requestMatchers("/students/add", "/students/save","/fees","/users/add", "/users/save").hasAuthority("ADMIN")  // ✅ Only ADMIN can add students
                        .anyRequest().authenticated())
                        .formLogin(login -> login.loginPage("/auth/login")  // Ensure this matches the controller method
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard on success
                        .failureUrl("/auth/login?error=true"))

                .logout(logout -> logout
                        .logoutUrl("/logout")  // Logout endpoint
                        .logoutSuccessUrl("/auth/login") // ✅ Redirect to login page after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ Encrypt passwords
    }

}
