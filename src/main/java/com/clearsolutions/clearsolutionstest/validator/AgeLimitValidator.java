package com.clearsolutions.clearsolutionstest.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.beans.factory.annotation.Value;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, LocalDate> {

  @Value("${app.user.required-age}")
  private Integer requiredAge;

  @Override
  public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
    LocalDate currentDate = LocalDate.now();

    return dateOfBirth == null || Period.between(dateOfBirth, currentDate).getYears() >= requiredAge;
  }
}
