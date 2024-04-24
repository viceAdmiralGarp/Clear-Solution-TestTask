package com.clearsolutions.clearsolutionstest.service;

import com.clearsolutions.clearsolutionstest.entity.User;
import com.clearsolutions.clearsolutionstest.model.UserDto;
import com.clearsolutions.clearsolutionstest.repo.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserDto> findUsersByBirthRange(LocalDate from, LocalDate to) {
    if (from.isAfter(to)) {
      log.error("From date must be before to date");
      throw new IllegalArgumentException("From date must be before to date");
    }
    return userRepository.findAllByDateOfBirthBetween(from, to);
  }

  @Override
  public User getUserById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  @Override
  public void createUser(UserDto userDto) {
    User saved = userRepository.save(userDto.toEntity());
    log.info("User created successfully {}", saved.getId());
  }

  @Override
  public void partialUpdateUser(UserDto userDto, UUID id) {
    userRepository.save(getUserById(id).copyProps(userDto));
    log.info("Partial user updated successfully {}", id);
  }

  @Override
  public void fullUpdateUser(UserDto userDto, UUID id) {
    userRepository.save(getUserById(id)).copyAllProps(userDto);
    log.info("Full user updated successfully {}", id);
  }

  @Override
  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
    log.info("User deleted successfully {}", id);
  }
}
