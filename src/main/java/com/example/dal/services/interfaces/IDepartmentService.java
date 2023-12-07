package com.example.dal.services.interfaces;

import com.example.dal.dto.DepartmentDto;

import java.util.List;

public interface IDepartmentService {
  List<DepartmentDto> getAllDepartments();
}
