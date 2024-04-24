package com.clearsolutions.clearsolutionstest.repo;

import com.clearsolutions.clearsolutionstest.entity.User;
import com.clearsolutions.clearsolutionstest.model.UserDto;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  List<UserDto> findAllByDateOfBirthBetween(LocalDate from, LocalDate localDate);
}
