package com.white.shoppinglist.appuser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank //same as not empty?
    @NotEmpty (message = "Password cannot be empty!")
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one number")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    private String password;
    
    public SignupRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
