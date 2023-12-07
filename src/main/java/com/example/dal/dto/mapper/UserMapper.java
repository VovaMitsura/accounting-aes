package com.example.dal.dto.mapper;

import com.example.dal.dto.UserDto;
import com.example.dal.entities.User;

public class UserMapper {
  public static UserDto toDto(User user) {
    return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getMiddleName(),
            user.getDateOfEmployment(),
            user.getRole().toString());
  }
}
