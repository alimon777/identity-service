package com.finance.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finance.identity.entity.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    @Query("SELECT u FROM UserCredential u WHERE u.username = :username")
    Optional<UserCredential> findByUsername(@Param("username") String username);
}