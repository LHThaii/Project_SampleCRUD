package com.example.project_samplecrud.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/category/**").permitAll() // Cho phép tất cả mọi người truy cập vào các endpoints bắt đầu với "/api/v1/category/"
                                .anyRequest().authenticated() // Các request khác yêu cầu xác thực
                )
                .csrf(csrf -> csrf.disable()); // Vô hiệu hóa CSRF nếu cần thiết
        return http.build();
    }
}
