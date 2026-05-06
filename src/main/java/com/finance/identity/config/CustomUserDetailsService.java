package com.finance.identity.repository;

import com.finance.identity.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    @Query("SELECT u FROM UserCredential u WHERE u.username = :username")
    Optional<UserCredential> findByUsername(@Param("username") String username);
}

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
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Optional<UserCredential> credential = repository.findByUsername(username);

        if (credential.isPresent()) {
            return new CustomUserDetails(credential.get());
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}