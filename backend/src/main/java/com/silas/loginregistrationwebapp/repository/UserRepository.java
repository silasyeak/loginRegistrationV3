package com.silas.loginregistrationwebapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silas.loginregistrationwebapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
