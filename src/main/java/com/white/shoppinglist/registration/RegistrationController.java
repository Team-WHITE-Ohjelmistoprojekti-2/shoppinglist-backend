package com.white.shoppinglist.registration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.white.shoppinglist.appuser.AppUserRepository;

import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
@RequestMapping(path = "/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final Validator validator;
    private final AppUserRepository appUserRepository;

    public RegistrationController(RegistrationService registrationService, Validator validator, AppUserRepository appUserRepository) {
        this.registrationService = registrationService;
        this.validator = validator;
        this.appUserRepository = appUserRepository;
    }

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        if (appUserRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        
        return ResponseEntity.ok(registrationService.register(request));
    }
}