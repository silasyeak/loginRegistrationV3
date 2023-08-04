package com.silas.loginregistrationwebapp.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silas.loginregistrationwebapp.dto.UserDto;
import com.silas.loginregistrationwebapp.model.Role;
import com.silas.loginregistrationwebapp.model.User;
import com.silas.loginregistrationwebapp.repository.RoleRepository;
import com.silas.loginregistrationwebapp.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setTelephone(userDto.getTelephone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = addNewRole();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role addNewRole(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }


    @Transactional
    public void changeUserRole(Long userId, String newRoleName) {
        // Find or create the role based on the newRoleName
        Role role = roleRepository.findByName(newRoleName);
        if (role == null) {
            role = new Role();
            role.setName(newRoleName);
            roleRepository.save(role);
        }

        // Find the user by user ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Assign the role to the user
        user.getRoles().add(role);

        // Check if the new role is an admin role
        if ("admin".equalsIgnoreCase(newRoleName)) {
            // Perform any specific actions for admin privileges, if needed
        }

        // Save the updated user back to the database
        userRepository.save(user);
    }
    
    private Role addNewRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
}