package com.silas.loginregistrationwebapp.service;

import com.silas.loginregistrationwebapp.model.Role;
import com.silas.loginregistrationwebapp.model.User;
import com.silas.loginregistrationwebapp.repository.RoleRepository;
import com.silas.loginregistrationwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void promoteUserToAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        user.getRoles().add(adminRole);
        userRepository.save(user);
    }

    @Transactional
    public void demoteAdminToUser(Long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + adminId));

        Role userRole = roleRepository.findByName("ROLE_USER");

        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        admin.getRoles().removeIf(role -> role.getName().equals("ADMIN"));
        admin.getRoles().add(userRole);
        userRepository.save(admin);
    }
}
