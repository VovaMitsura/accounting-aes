package com.example.dal.repositories.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.entities.Address;
import com.example.dal.repositories.interfaces.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryImpl extends BaseRepository<Address> implements IAddressRepository {
  public AddressRepositoryImpl(@Autowired EntityManagerUtil entityManagerUtil) {
    super(entityManagerUtil);
  }
}
