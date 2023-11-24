package com.example.dal.repositories.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.entities.NuclearPowerPlant;
import com.example.dal.repositories.interfaces.INuclearPowerPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NuclearPowerPlantRepositoryImpl extends BaseRepository<NuclearPowerPlant> implements INuclearPowerPlantRepository {
  public NuclearPowerPlantRepositoryImpl(@Autowired EntityManagerUtil entityManagerUtil) {
    super(entityManagerUtil);
  }
}
