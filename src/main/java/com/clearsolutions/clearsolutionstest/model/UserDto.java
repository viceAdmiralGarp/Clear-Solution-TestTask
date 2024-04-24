package com.clearsolutions.clearsolutionstest.model;

import com.clearsolutions.clearsolutionstest.entity.User;
import com.clearsolutions.clearsolutionstest.validator.AgeLimit;
import com.clearsolutions.clearsolutionstest.validator.NotAfterCurrent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UserDto(
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    String email,
    @NotBlank(message = "First name is required")
    String firstName,
    @NotBlank(message = "Last name is required")
    String lastName,
    @AgeLimit
    @NotAfterCurrent
    @NotNull(message = "Date of birth is required")
    LocalDate dateOfBirth,
    String address,
    String phoneNumber
) {

  public User toEntity() {
    return User.builder()
        .email(email)
        .firstName(firstName)
        .lastName(lastName)
        .dateOfBirth(dateOfBirth)
        .address(address)
        .phoneNumber(phoneNumber)
        .build();
  }
}
