package com.example.project_samplecrud.Security;

import com.example.project_samplecrud.Entities.Role;
import com.example.project_samplecrud.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor

public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Lấy danh sách các roles của user và chuyển đổi thành Collection của GrantedAuthority
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())) // Sử dụng đúng thuộc tính của Role để tạo GrantedAuthority
                .collect(Collectors.toList()); // Chuyển đổi thành danh sách
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getFullname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
