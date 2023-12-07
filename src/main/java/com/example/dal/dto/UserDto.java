package com.example.dal.dto;

import java.util.Date;
import java.util.UUID;

public record UserDto (UUID id,
                       String email,
                       String firstName,
                       String lastName,
                       String middleName,
                       Date dateOfEmployment,
                       String role) {
}
