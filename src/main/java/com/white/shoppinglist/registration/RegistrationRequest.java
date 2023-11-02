package com.white.shoppinglist.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String username = "";

    @NotBlank
    @Size(min = 6)
    private String password = "";

    private String description = "";

    public RegistrationRequest(String username, String password, String description) {
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) &&
                password.equals(that.password) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
