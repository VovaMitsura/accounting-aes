package com.example.dal.repositories.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.entities.Address;
import com.example.dal.repositories.interfaces.IAddressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AddressRepositoryImpl.class, EntityManagerUtil.class})
class AddressRepositoryImplTest {

  @MockBean
  SessionFactory entityManagerFactory;

  @MockBean
  EntityManager entityManager;

  @MockBean
  EntityTransaction entityTransaction;

  @Autowired
  IAddressRepository addressRepository;


  @BeforeEach
  void setUp() {
    Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    Mockito.when(entityManager.getTransaction()).thenReturn(entityTransaction);
    Mockito.doNothing().when(entityTransaction).begin();
    Mockito.doNothing().when(entityTransaction).commit();
    Mockito.doNothing().when(entityTransaction).rollback();
    Mockito.doNothing().when(entityManager).close();
  }

  @Test
  void save_new_address_should_call_entity_manager_persist() {
    // Arrange
    var address = Address.builder()
            .apartmentNumber("1")
            .city("city")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    Mockito.doNothing().when(entityManager).persist(address);

    // Act
    addressRepository.save(address);

    // Assert
    Mockito.verify(entityManager, Mockito.times(1)).persist(address);
  }

  @Test
  void save_null_address_should_throw_null_pointer_exception() {
    // Arrange
    Address address = null;

    // Act
    var error = Assertions.assertThrows(NullPointerException.class, () -> addressRepository.save(address));

    // Assert
    Assertions.assertNull(error.getMessage());
  }

  @Test
  void find_by_id_existing_address_should_call_entity_manager_find_and_return_address() {
    // Arrange
    var address = Address.builder()
            .apartmentNumber("1")
            .city("city")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    Mockito.when(entityManager.find(Address.class, address.getId())).thenReturn(address);

    // Act
    var result = addressRepository.findById(address.getId());

    // Assert
    Mockito.verify(entityManager, Mockito.times(1)).find(Address.class, address.getId());
    Assertions.assertEquals(address, result.get());
  }

  @Test
  void find_by_predicate_city_should_call_entity_manager_create_query_and_return_addresses() {
    // Arrange
    var address1 = Address.builder()
            .apartmentNumber("1")
            .city("city1")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();
    var address2 = Address.builder()
            .apartmentNumber("1")
            .city("city2")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    var query = Mockito.mock(TypedQuery.class);
    Mockito.when(entityManager.createQuery("from " + Address.class.getName(), Address.class)).thenReturn(query);
    Mockito.when(query.getResultList()).thenReturn(List.of(address1, address2));


    // Act
    var result = addressRepository.findByPredicate(a -> a.getCity().equals("city1"));

    // Assert
    Mockito.verify(entityManager, Mockito.times(1)).createQuery("from " + Address.class.getName(), Address.class);
    Assertions.assertEquals(address1, result.get());
  }

  @Test
  void find_all_should_call_entity_manager_query_for_all_and_return_list() {
    // Arrange
    var address = Address.builder()
            .apartmentNumber("1")
            .city("city")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    var query = Mockito.mock(TypedQuery.class);
    Mockito.when(entityManager.createQuery("from " + Address.class.getName())).thenReturn(query);
    Mockito.when(query.getResultList()).thenReturn(List.of(address));


    // Act
    var result = addressRepository.findAll();

    // Assert
    Assertions.assertEquals(java.util.List.of(address), result);
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(address, result.get(0));
  }

  @Test
  void update_existing_address_should_call_entity_manager_merge() {
    // Arrange
    var address = Address.builder()
            .apartmentNumber("1")
            .city("city")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    Mockito.when(entityManager.merge(address)).thenReturn(address);


    // Act
    addressRepository.update(address);

    // Assert
    Mockito.verify(entityManager, Mockito.times(1)).merge(address);
  }

  @Test
  void delete_existing_address_should_call_entity_manager_remove() {
    // Arrange
    var address = Address.builder()
            .apartmentNumber("1")
            .city("city")
            .buildingNumber("1")
            .zipCode("12345")
            .street("street")
            .build();

    Mockito.doNothing().when(entityManager).remove(address);

    // Act
    addressRepository.delete(address);

    // Assert
    Mockito.verify(entityManager, Mockito.times(1)).remove(address);
  }
}
