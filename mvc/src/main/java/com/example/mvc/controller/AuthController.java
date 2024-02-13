package com.example.mvc.controller;

import com.example.mvc.dto.RegistrationDto;
import com.example.mvc.models.UserEntity;
import com.example.mvc.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String getRegisterForm(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult bindingResult, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            System.out.println(bindingResult.getAllErrors());
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()){
            System.out.println(bindingResult.getAllErrors());
            return "redirect:/register?fail";

        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            System.out.println(bindingResult.getAllErrors());
            return "register";
        }
        System.out.println("registration success");
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
