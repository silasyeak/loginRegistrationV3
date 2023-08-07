package com.silas.loginregistrationwebapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.silas.loginregistrationwebapp.dto.UserDto;
import com.silas.loginregistrationwebapp.model.User;
import com.silas.loginregistrationwebapp.service.UserService;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    } 

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);

        // Omitted Roles
        //        String roles = user.getRole();
        //        model.addAttribute("roles", roles);

        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/manager")
    public String managerPage(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());

        // Check if the user is a manager (assuming role is a String property in User)
        if (user.getRole().equalsIgnoreCase("Manager")) {
            model.addAttribute("user", user);
            model.addAttribute("role", user);
            model.addAttribute("listUsers", userService.getAllUsers());
//            // Add any additional data you want to show on the manager page
            return "manager";
        } else {
            // If the user is not a manager, you can redirect them to another page or show an error message
            return "redirect:/";
        }
    }

}
