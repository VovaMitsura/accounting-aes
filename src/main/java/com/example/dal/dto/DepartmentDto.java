package com.example.dal.dto;

import java.util.List;
import java.util.UUID;

public record DepartmentDto(UUID id,
                            AddressDto address,
                            List<UserDto> employees) {
}
