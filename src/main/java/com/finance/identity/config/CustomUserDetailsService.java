package com.finance.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.finance.identity.entity.UserCredential;
import com.finance.identity.repository.UserCredentialRepository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = repository.findByUsername(username);

        if (credential.isPresent()) {
            return new CustomUserDetails(credential.get());
        } else {
            // Log the failed login attempt without revealing the specific reason
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}