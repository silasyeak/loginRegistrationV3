package com.silas.loginregistrationwebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.silas.loginregistrationwebapp.dto.UserDto;
import com.silas.loginregistrationwebapp.dto.UserDtoNoExceptions;
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
    
    @PostMapping("/saveUser")
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setTelephone(userDto.getTelephone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("User");
        userRepository.save(user);
    }
    
    @PostMapping("/updateUser")
    public String updateUser(UserDtoNoExceptions userDtoNE) {
    	 User user = new User();
         user.setName(userDtoNE.getName());
         user.setTelephone(userDtoNE.getTelephone());
         user.setEmail(userDtoNE.getEmail());
         //user.setPassword(passwordEncoder.encode(userDtoNE.getPassword()));
         user.setRole(userDtoNE.getRole());
         userRepository.save(user);
         return "redirect:/manager";
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findUserById(long id) {
    	Optional<User> optional = userRepository.findById(id);
    	User user = null;
    	if(optional.isPresent()) {
    		user = optional.get();
    	}else {
    		throw new RuntimeException(" User not found for id :: " + id);
    	}
    	return user;
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