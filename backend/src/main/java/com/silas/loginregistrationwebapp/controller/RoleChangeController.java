package com.silas.loginregistrationwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silas.loginregistrationwebapp.service.UserService;

@RestController
@RequestMapping("/api")
public class RoleChangeController {

    private final UserService userService;

    @Autowired
    public RoleChangeController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/change-role/{userId}")
    public ResponseEntity<String> changeUserRole(@PathVariable Long userId, @RequestParam String newRole) {
        userService.changeUserRole(userId, newRole);
        return new ResponseEntity<>("User role changed successfully.", HttpStatus.OK);
    }
    
    // This route require basic auth, Only ADMIN can access
    @GetMapping("/message")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getMessage() {
        return "Hey, Admin";
    }

}
