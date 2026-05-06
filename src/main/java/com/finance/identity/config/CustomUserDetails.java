package com.finance.identity.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finance.identity.entity.UserCredential;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomUserDetails(UserCredential userCredential) {
        if (userCredential == null || userCredential.getUsername() == null || userCredential.getPassword() == null) {
            throw new IllegalArgumentException("UserCredential and its fields must not be null");
        }
        this.username = sanitizeInput(userCredential.getUsername());
        this.password = sanitizeInput(userCredential.getPassword());
        this.accountNonExpired = userCredential.isAccountNonExpired();
        this.accountNonLocked = userCredential.isAccountNonLocked();
        this.credentialsNonExpired = userCredential.isCredentialsNonExpired();
        this.enabled = userCredential.isEnabled();
    }

    private String sanitizeInput(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}