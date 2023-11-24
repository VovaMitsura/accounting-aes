package com.example.dal.unit_of_work.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.repositories.impl.AddressRepositoryImpl;
import com.example.dal.repositories.impl.DepartmentRepositoryImpl;
import com.example.dal.repositories.impl.NuclearPowerPlantRepositoryImpl;
import com.example.dal.repositories.impl.UserRepositoryImpl;
import com.example.dal.repositories.interfaces.IAddressRepository;
import com.example.dal.repositories.interfaces.IDepartmentRepository;
import com.example.dal.repositories.interfaces.INuclearPowerPlantRepository;
import com.example.dal.repositories.interfaces.IUserRepository;
import com.example.dal.unit_of_work.IUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class UnitOfWorkImpl implements IUnitOfWork {

  private IAddressRepository addressRepository;
  private IDepartmentRepository departmentRepository;
  private INuclearPowerPlantRepository nuclearPowerPlantRepository;
  private IUserRepository userRepository;
  private final EntityManagerUtil entityManagerUtil;

  public UnitOfWorkImpl(EntityManagerUtil entityManagerUtil) {
    this.entityManagerUtil = entityManagerUtil;
  }

  @Override
  public IAddressRepository getAddressRepository() {
    if (addressRepository == null)
      addressRepository = new AddressRepositoryImpl(entityManagerUtil);
    return addressRepository;
  }

  @Override
  public IDepartmentRepository getDepartmentRepository() {
    if (departmentRepository == null)
      departmentRepository = new DepartmentRepositoryImpl(entityManagerUtil);
    return departmentRepository;
  }

  @Override
  public INuclearPowerPlantRepository getNuclearPowerPlantRepository() {
    if (nuclearPowerPlantRepository == null)
      nuclearPowerPlantRepository = new NuclearPowerPlantRepositoryImpl(entityManagerUtil);
    return nuclearPowerPlantRepository;
  }

  @Override
  public IUserRepository getUserRepository() {
    if (userRepository == null)
      userRepository = new UserRepositoryImpl(entityManagerUtil);
    return userRepository;
  }

  @Override
  public void saveChanges() {
    entityManagerUtil.performWithinTx(entityManager -> {
      entityManager.flush();
      entityManager.clear();
    });
  }

  @Override
  public void close() {
    entityManagerUtil.performWithinTx(entityManager -> {
      entityManager.close();
      entityManager.getEntityManagerFactory().close();
    });
  }
}
