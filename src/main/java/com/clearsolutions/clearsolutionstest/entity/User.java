package com.clearsolutions.clearsolutionstest.entity;

import com.clearsolutions.clearsolutionstest.model.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "`user`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  public User copyAllProps(UserDto userDto) {
    this.email = userDto.email();
    this.firstName = userDto.firstName();
    this.lastName = userDto.lastName();
    this.dateOfBirth = userDto.dateOfBirth();
    this.address = userDto.address();
    this.phoneNumber = userDto.phoneNumber();
    return this;
  }

  public User copyProps(UserDto userDto) {
    this.email = userDto.email() != null ? userDto.email() : this.email;
    this.firstName = userDto.firstName() != null ? userDto.firstName() : this.firstName;
    this.lastName = userDto.lastName() != null ? userDto.lastName() : this.lastName;
    this.dateOfBirth = userDto.dateOfBirth() != null ? userDto.dateOfBirth() : this.dateOfBirth;
    this.address = userDto.address() != null ? userDto.address() : this.address;
    this.phoneNumber = userDto.phoneNumber() != null ? userDto.phoneNumber() : this.phoneNumber;
    return this;
  }
}
