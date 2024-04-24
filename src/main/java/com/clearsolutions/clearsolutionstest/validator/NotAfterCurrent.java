package com.clearsolutions.clearsolutionstest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotAfterCurrentValidator.class)
public @interface NotAfterCurrent {

  String message() default "Date must not be after current date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
