package com.example.dal.services.impl;


import com.example.dal.entities.Address;
import com.example.dal.entities.Department;
import com.example.dal.entities.Role;
import com.example.dal.entities.User;
import com.example.dal.repositories.interfaces.IDepartmentRepository;
import com.example.dal.security.SecurityContactHolder;
import com.example.dal.security.SecurityContextImpl;
import com.example.dal.security.UserEmailPasswordAuthenticationImpl;
import com.example.dal.unit_of_work.impl.UnitOfWorkImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class DepartmentServiceTest {

  @MockBean
  private UnitOfWorkImpl unitOfWork;

  @MockBean
  private IDepartmentRepository departmentRepository;

  private DepartmentService departmentService;
  private User user;

  private void setupSecurityContext(Role role) {
    user = User.builder()
            .id(UUID.randomUUID())
            .role(role)
            .build();
    UserEmailPasswordAuthenticationImpl authentication = new UserEmailPasswordAuthenticationImpl(user);
    SecurityContextImpl securityContext = new SecurityContextImpl(authentication);
    SecurityContactHolder.setContext(securityContext);
  }

  @BeforeEach
  void setUp() {
    departmentService = new DepartmentService(unitOfWork);
  }

  @Test
  void initDepartment_unitOfWorkIsNull_throwsIllegalArgumentException() {
    // Arrange
    UnitOfWorkImpl unitOfWork = null;

    // Act
    var error = Assertions.assertThrows(IllegalArgumentException.class, () -> new DepartmentService(unitOfWork));

    // Assert
    Assertions.assertEquals("argument unitOfWork is null", error.getMessage());
  }

  @Test
  void getDepartments_userRoleIsAdmin_throwsSecurityException() {
    // Arrange
    setupSecurityContext(Role.ADMIN);

    // Act
    var error = Assertions.assertThrows(SecurityException.class, departmentService::getAllDepartments);

    // Assert
    Assertions.assertEquals("User is not authorized to perform this action", error.getMessage());
  }

  @Test
  void getDepartments_userRoleIsHeadOfDepartment_returnsDepartments() {
    // Arrange
    setupSecurityContext(Role.HEAD_OF_DEPARTMENT);

    var address1 = Address.builder().id(UUID.randomUUID()).city("city").street("street").buildingNumber("buildingNumber").apartmentNumber("apartmentNumber1").build();
    var address2 = Address.builder().id(UUID.randomUUID()).city("city").street("street").buildingNumber("buildingNumber").apartmentNumber("apartmentNumber2").build();

    var department1 = Department.builder().id(UUID.randomUUID()).address(address1).employees(List.of(user)).build();
    var department2 = Department.builder().id(UUID.randomUUID()).address(address2).employees(List.of(user)).build();

    Mockito.when(unitOfWork.getDepartmentRepository()).thenReturn(departmentRepository);
    Mockito.when(unitOfWork.getDepartmentRepository().findAll()).thenReturn(List.of(department1, department2));


    Mockito.when(unitOfWork.getDepartmentRepository().findAll()).thenReturn(List.of(department1, department2));

    // Act
    var departments = departmentService.getAllDepartments();

    // Assert
    Assertions.assertNotNull(departments);
    Assertions.assertEquals(2, departments.size());
    Assertions.assertEquals(address1.getApartmentNumber(), departments.get(0).address().apartmentNumber());
    Assertions.assertEquals(address2.getApartmentNumber(), departments.get(1).address().apartmentNumber());
  }


}