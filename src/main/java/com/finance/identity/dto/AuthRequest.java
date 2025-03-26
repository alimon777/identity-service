package com.finance.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,30}$");

    private String username;
    private char[] password;

    public void setUsername(String username) {
        if (username == null || !USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    public void setPassword(char[] password) {
        if (password == null || !PASSWORD_PATTERN.matcher(new String(password)).matches()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.password = password;
    }

    public void clearPassword() {
        if (this.password != null) {
            Arrays.fill(this.password, '\0');
        }
    }
}