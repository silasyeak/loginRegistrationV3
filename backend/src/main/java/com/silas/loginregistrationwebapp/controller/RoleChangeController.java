package com.silas.loginregistrationwebapp.controller;

import com.silas.loginregistrationwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
