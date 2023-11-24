package com.example.dal;

import com.example.dal.unit_of_work.UnitOfWorkImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DalApplicationTests {

  @Autowired
  UnitOfWorkImpl unitOfWork;

  @Test
  void contextLoads() {
    var addressRepository = unitOfWork.getAddressRepository();
    var list = addressRepository.findAll();
    System.out.println(list);
    var address = addressRepository.findById(list.get(0).getId());
    System.out.println(address);
  }

}
