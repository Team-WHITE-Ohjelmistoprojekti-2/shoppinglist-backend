package com.white.shoppinglist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    // Higher strength = more secure, but takes longer time to compute.
    // 10 - 12 is usually good.
    private final static int PASSWORD_ENCODE_STRENGTH = 12;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_ENCODE_STRENGTH);
    }
}