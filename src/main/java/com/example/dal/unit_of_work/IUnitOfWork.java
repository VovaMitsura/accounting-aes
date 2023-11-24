package com.example.dal.unit_of_work;

import com.example.dal.repositories.interfaces.IAddressRepository;
import com.example.dal.repositories.interfaces.IDepartmentRepository;
import com.example.dal.repositories.interfaces.INuclearPowerPlantRepository;
import com.example.dal.repositories.interfaces.IUserRepository;

public interface IUnitOfWork {

  IAddressRepository getAddressRepository();

  IDepartmentRepository getDepartmentRepository();

  INuclearPowerPlantRepository getNuclearPowerPlantRepository();

  IUserRepository getUserRepository();

  void saveChanges();

  void close();

}
