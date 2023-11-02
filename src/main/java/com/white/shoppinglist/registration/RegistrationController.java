package com.white.shoppinglist.registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
@RequestMapping(path = "/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final Validator validator;

    public RegistrationController(RegistrationService registrationService, Validator validator) {
        this.registrationService = registrationService;
        this.validator = validator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}