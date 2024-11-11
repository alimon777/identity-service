package com.finance.identity.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finance.identity.dto.UserResponse;
import com.finance.identity.entity.UserCredential;
import com.finance.identity.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public UserResponse getLoginnedUserDetails(String username) {
    	UserCredential user = repository.findByUsername(username).get();
    	UserResponse userDetails = new UserResponse();
    	userDetails.setUserId(user.getId());
    	userDetails.setUsername(user.getUsername());
    	return userDetails;
    }
}
