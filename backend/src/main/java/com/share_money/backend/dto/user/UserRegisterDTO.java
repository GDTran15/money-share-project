package com.share_money.backend.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDate;

public record UserRegisterDTO(
        @NotBlank(message = "Please enter a username")
        String username,
        @NotBlank(message = "Please enter a password")
        String password,
        @NotBlank(message = "Please enter your email")
        String email,
        LocalDate dateOfBirth

) {
}
