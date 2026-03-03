package com.example.demo.controller;

import com.example.demo.entity.Registration;
import com.example.demo.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Registration registration) {
        registrationService.saveRegistration(registration);
        return "redirect:/my-registrations";
    }

    @GetMapping("/my-registrations")
    public String viewRegistrations(Model model) {
        model.addAttribute("registrations", registrationService.getAllRegistrations());
        return "my-registrations";
    }
}
