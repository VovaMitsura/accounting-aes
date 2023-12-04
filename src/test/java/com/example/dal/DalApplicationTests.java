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

  @Test
  void find_all_addresses_return_list_of_addresses() {
    var addressesRepo = unitOfWork.getAddressRepository();
    var addresses = addressesRepo.findAll();
    Assertions.assertEquals(1, addresses.size());
  }

  @Test
  void find_address_by_id_return_address() {
    var addressesRepo = unitOfWork.getAddressRepository();
    var addresses = addressesRepo.findAll();
    var optionalAddress = addressesRepo.findById(addresses.get(0).getId());
    Assertions.assertTrue(optionalAddress.isPresent());
    Assertions.assertEquals(addresses.get(0).getId(), optionalAddress.get().getId());
  }

  @Test
  void find_by_predicate_return_list_of_addresses() {
    var addressesRepo = unitOfWork.getAddressRepository();
    var addresses = addressesRepo.findAll();
    Predicate<Address> predicate = address -> address.getCity().equals("Sumy");
    var optionalAddress = addressesRepo.findByPredicate(predicate);
    Assertions.assertTrue(optionalAddress.isPresent());
  }

  @Test
  void update_address_should_change_city_to_Kharkiv() {
    var addressesRepo = unitOfWork.getAddressRepository();
    var addresses = addressesRepo.findAll();
    var address = addresses.get(0);
    address.setCity("Kharkiv");
    addressesRepo.update(address);
    var optionalAddress = addressesRepo.findById(address.getId());
    Assertions.assertTrue(optionalAddress.isPresent());
    Assertions.assertEquals("Kharkiv", optionalAddress.get().getCity());
  }

  @Test
    void delete_address_should_delete_address() {
        var addressesRepo = unitOfWork.getAddressRepository();
        var addresses = addressesRepo.findAll();
        var address = addresses.get(0);
        addressesRepo.delete(address);
        var optionalAddress = addressesRepo.findById(address.getId());
        Assertions.assertFalse(optionalAddress.isPresent());
    }
}
