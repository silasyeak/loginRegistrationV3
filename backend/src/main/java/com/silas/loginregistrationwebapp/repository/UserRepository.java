package com.silas.loginregistrationwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silas.loginregistrationwebapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}