package com.finance.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.identity.entity.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Long> {
    Optional<UserCredential> findByUsername(String username);
}