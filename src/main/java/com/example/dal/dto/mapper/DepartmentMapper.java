package com.example.dal.dto.mapper;

import com.example.dal.dto.DepartmentDto;
import com.example.dal.entities.Department;

public class DepartmentMapper {

  public static DepartmentDto toDto(Department department) {
    var addressDto = AddressMapper.toDto(department.getAddress());
    var employees = department.getEmployees().stream().map(UserMapper::toDto).toList();
    return new DepartmentDto(department.getId(),
            addressDto,
            employees);
  }
}
