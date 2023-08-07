package com.silas.loginregistrationwebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.silas.loginregistrationwebapp.dto.UserDto;
import com.silas.loginregistrationwebapp.model.User;
import com.silas.loginregistrationwebapp.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/saveUser") //test this mapping, or maybe create a new one
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setTelephone(userDto.getTelephone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("User");
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateManager(Long id, String manager) {
        // Check if the user exists in the database
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(manager);
            userRepository.save(user);
        }
    }
    
    public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}