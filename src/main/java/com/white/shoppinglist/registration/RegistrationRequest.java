package com.white.shoppinglist.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @NotBlank(message = "Username must contain at least one non-whitespace character")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 2, max = 20, message = "Username must be between 2-20 characters")
    private String username = "";

    @NotBlank(message = "Password must contain at least one non-whitespace character")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must have at least 6 characters")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one number")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter between A-Z")
    private String password = "";

    private String description = "";
}
