package com.clearsolutions.clearsolutionstest.service;

import com.clearsolutions.clearsolutionstest.entity.User;
import com.clearsolutions.clearsolutionstest.model.UserDto;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserService {

  List<UserDto> findUsersByBirthRange(LocalDate from, LocalDate to);

  User getUserById(UUID id);

  void createUser(UserDto userDto);

  void partialUpdateUser(UserDto userDto, UUID id);

  void fullUpdateUser(UserDto userDto, UUID id);

  void deleteUser(UUID id);
}
