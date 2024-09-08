package com.example.project_samplecrud.Security;

import com.example.project_samplecrud.Entities.User;
import com.example.project_samplecrud.Repository.UserRepository;
import com.example.project_samplecrud.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users =  userRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new CustomUserDetails(users);
    }
}
