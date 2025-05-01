package com.example.superApp.payUrFren.repository;

import com.example.superApp.payUrFren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BaseUserRepository extends JpaRepository<User, UUID> {
    /**
     * Find a user by username
     * @param username The username to search for
     * @return An Optional containing the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by email
     * @param email The email to search for
     * @return An Optional containing the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by phone number
     * @param phone The phone number to search for
     * @return An Optional containing the user if found
     */
    Optional<User> findByPhone(String phone);

    /**
     * Check if a username already exists
     * @param username The username to check
     * @return True if the username exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if an email already exists
     * @param email The email to check
     * @return True if the email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if a phone number already exists
     * @param phone The phone number to check
     * @return True if the phone number exists
     */
    boolean existsByPhone(String phone);
}


