package com.example.dal.repositories.impl;

import com.example.dal.config.util.EntityManagerUtil;
import com.example.dal.entities.User;
import com.example.dal.repositories.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends BaseRepository<User> implements IUserRepository {
  public UserRepositoryImpl(@Autowired EntityManagerUtil entityManagerUtil) {
    super(entityManagerUtil);
  }
}
