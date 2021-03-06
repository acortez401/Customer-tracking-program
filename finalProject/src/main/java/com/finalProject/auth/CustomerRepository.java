package com.finalProject.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);

	User save(User newUser);
}