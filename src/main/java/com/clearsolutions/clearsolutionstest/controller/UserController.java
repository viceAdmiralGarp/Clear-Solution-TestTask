package com.clearsolutions.clearsolutionstest.controller;

import com.clearsolutions.clearsolutionstest.model.UserDto;
import com.clearsolutions.clearsolutionstest.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity getUsersByBirthRange(@RequestParam LocalDate from,
      @RequestParam LocalDate to) {
    return ResponseEntity.ok(userService.findUsersByBirthRange(from, to));
  }

  @PostMapping
  public ResponseEntity createUser(@Valid @RequestBody UserDto userDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<ObjectError> allErrors = bindingResult.getAllErrors();
      return ResponseEntity.badRequest()
          .body(allErrors.stream().map(ObjectError::getDefaultMessage));
    }
    userService.createUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity partialUpdateUser(@RequestBody UserDto userDto, @PathVariable UUID id) {
    userService.partialUpdateUser(userDto, id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity fullUpdateUser(@RequestBody @Valid UserDto userDto, @PathVariable UUID id,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<ObjectError> allErrors = bindingResult.getAllErrors();
      return ResponseEntity.badRequest()
          .body(allErrors.stream().map(ObjectError::getDefaultMessage));
    }
    userService.fullUpdateUser(userDto, id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable UUID id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}
