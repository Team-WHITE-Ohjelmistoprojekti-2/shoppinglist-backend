package com.white.shoppinglist.registration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.white.shoppinglist.appuser.AppUserRepository;
import com.white.shoppinglist.security.auth.JwtService;

import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
@RequestMapping(path = "/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final Validator validator;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistrationController(RegistrationService registrationService, Validator validator,
            AppUserRepository appUserRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.validator = validator;
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    // User login. Returns JWT token if login successful.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());
        Authentication auth = authenticationManager.authenticate(creds);
        // Generate token
        String jwts = jwtService.getToken(auth.getName());

        // Build response with the generated token
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
    }
}