package com.example.dal;

import com.example.dal.entities.Address;
import com.example.dal.unit_of_work.impl.UnitOfWorkImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Predicate;

@SpringBootTest
class DalApplicationTests {

  @Autowired
  UnitOfWorkImpl unitOfWork;

  @Test
  void context_loads() {

  }

}
