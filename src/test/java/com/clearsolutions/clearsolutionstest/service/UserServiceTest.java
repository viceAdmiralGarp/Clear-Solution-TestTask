package com.clearsolutions.clearsolutionstest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.clearsolutions.clearsolutionstest.entity.User;
import com.clearsolutions.clearsolutionstest.model.UserDto;
import com.clearsolutions.clearsolutionstest.repo.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindUserById() {
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setFirstName("John");
    user.setLastName("Doe");

    when(userRepository.findById(any())).thenReturn(Optional.of(user));

    User foundUser = userService.getUserById(any());

    assertEquals(user.getId(), foundUser.getId());
    assertEquals(user.getFirstName(), foundUser.getFirstName());
    assertEquals(user.getLastName(), foundUser.getLastName());
  }

  @Test
  public void testFindUserById_notFound() {
    when(userRepository.findById(any())).thenReturn(Optional.empty());

    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> userService.getUserById(any()));

    assertEquals("User not found", exception.getMessage());
  }

  @Test
  public void testFindUsersByBirthRange() {
    UserDto userDto = new UserDto("test@email.com", "John", "Doe",
        LocalDate.parse("2007-04-23"), null, null);

    when(userRepository.findAllByDateOfBirthBetween(any(),
        any())).thenReturn(List.of(userDto));

    List<UserDto> userDtos = userService.findUsersByBirthRange(LocalDate.parse("2006-04-23"),
        LocalDate.parse("2008-04-23"));

    assertEquals(1, userDtos.size());
    assertEquals(userDto.firstName(), userDtos.get(0).firstName());
    assertEquals(userDto.lastName(), userDtos.get(0).lastName());
    assertEquals(userDto.email(), userDtos.get(0).email());
    assertEquals(userDto.dateOfBirth(), userDtos.get(0).dateOfBirth());
  }

  @Test
  public void testFindUsersByBirthRange_invalidInput() {
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> userService.findUsersByBirthRange(LocalDate.parse("2008-04-23"),
            LocalDate.parse("2006-04-23")));

    assertEquals("From date must be before to date", exception.getMessage());
  }

  @Test
  public void testCreateUser() {
    User user = new User();
    user.setId(UUID.randomUUID());

    UserDto userDto = new UserDto("test@email.com", "John", "Doe",
        LocalDate.parse("2006-04-23"), null, null);

    when(userRepository.save(any())).thenReturn(user);

    userService.createUser(userDto);

    verify(userRepository).save(any());
  }

  @Test
  public void testPartialUpdateUser() {
    User user = new User();

    UserDto userDto = new UserDto("test@email.com", "John", "Doe",
        LocalDate.parse("2006-04-23"), null, null);

    when(userRepository.save(any())).thenReturn(user);
    when(userRepository.findById(any())).thenReturn(Optional.of(user));

    userService.partialUpdateUser(userDto, UUID.randomUUID());

    verify(userRepository).save(any());
    verify(userRepository).findById(any());
  }

  @Test
  public void testFullUpdateUser() {
    User user = new User();

    UserDto userDto = new UserDto("test@email.com", "John", "Doe",
        LocalDate.parse("2006-04-23"), null, null);

    when(userRepository.save(any())).thenReturn(user);
    when(userRepository.findById(any())).thenReturn(Optional.of(user));

    userService.fullUpdateUser(userDto, UUID.randomUUID());

    verify(userRepository).save(any());
    verify(userRepository).findById(any());
  }

  @Test
  public void testDeleteUser() {
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setFirstName("John");
    user.setLastName("Doe");

    doNothing().when(userRepository).deleteById(any());

    userService.deleteUser(any());

    verify(userRepository).deleteById(any());
  }
}