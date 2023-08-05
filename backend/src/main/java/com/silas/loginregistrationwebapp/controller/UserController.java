package com.silas.loginregistrationwebapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silas.loginregistrationwebapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // Constructor injection of UserService (You need to create the UserService class separately)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateManager(@PathVariable Long id, @RequestBody String role) {
        try {
            userService.updateManager(id, role);
            return ResponseEntity.ok("Manager updated successfully.");
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
