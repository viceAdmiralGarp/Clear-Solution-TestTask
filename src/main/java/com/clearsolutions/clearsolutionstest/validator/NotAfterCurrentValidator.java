package com.clearsolutions.clearsolutionstest.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotAfterCurrentValidator implements ConstraintValidator<NotAfterCurrent, LocalDate>{

  @Override
  public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
    return dateOfBirth == null || dateOfBirth.isBefore(LocalDate.now());
  }
}
