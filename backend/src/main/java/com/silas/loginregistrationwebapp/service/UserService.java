package com.silas.loginregistrationwebapp.service;

import java.util.Arrays;

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
        user.setRoles("ROLE_ADMIN");
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
    public void changeUserRole(Long userId, String newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Validate the new role (You can add more sophisticated validation if needed)
        if (!newRole.equalsIgnoreCase("ROLE_ADMIN") && !newRole.equalsIgnoreCase("ROLE_USER")) {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }

        user.setRoles(newRole); // Assuming you have a "role" field in the User model
        userRepository.save(user);
    }
}