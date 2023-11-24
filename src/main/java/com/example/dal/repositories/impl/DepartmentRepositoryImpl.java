package com.example.dal.repositories.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.entities.Department;
import com.example.dal.repositories.interfaces.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DepartmentRepositoryImpl extends BaseRepository<Department, UUID> implements IDepartmentRepository {
  public DepartmentRepositoryImpl(@Autowired EntityManagerUtil entityManagerUtil) {
    super(entityManagerUtil);
  }
}
