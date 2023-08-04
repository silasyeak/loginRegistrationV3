package com.silas.loginregistrationwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silas.loginregistrationwebapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}