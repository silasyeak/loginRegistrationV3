package com.silas.loginregistrationwebapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.silas.loginregistrationwebapp.model.User;
import com.silas.loginregistrationwebapp.model.Role;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRoles(Role role);

}
