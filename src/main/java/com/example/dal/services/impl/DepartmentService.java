package com.example.dal.services.impl;

import com.example.dal.dto.DepartmentDto;
import com.example.dal.dto.mapper.DepartmentMapper;
import com.example.dal.entities.Role;
import com.example.dal.security.SecurityContactHolder;
import com.example.dal.services.interfaces.IDepartmentService;
import com.example.dal.unit_of_work.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

  private final IUnitOfWork database;

  public DepartmentService(IUnitOfWork unitOfWork) {
    if (unitOfWork == null)
      throw new IllegalArgumentException("argument unitOfWork is null");
    this.database = unitOfWork;
  }

  @Override
  public List<DepartmentDto> getAllDepartments() {
    var user = SecurityContactHolder.getContext().getAuthentication().getUserDetails();
    var userAuthorities = user.getUserAuthorities();
    var userRoles = userAuthorities.
            stream().
            map(auth -> Role.valueOf(auth.getAuthority())).
            toList();

    if (!(
            userRoles.contains(Role.ADMIN) ||
            userRoles.contains(Role.HEAD_OF_DEPARTMENT) ||
            userRoles.contains(Role.ACCOUNTANT)))
      throw new SecurityException("User is not authorized to perform this action");

    var departments = database.getDepartmentRepository().findAll();
    return departments.stream().map(DepartmentMapper::toDto).toList();
  }
}
