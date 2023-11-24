package com.example.dal.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IRepository<T, U> {

  void save(T obj);

  Optional<T> findById(U id);
  Optional<T> findByPredicate(T obj, Predicate<T> predicate);

  List<T> findAll();

  void update(T obj);

  void delete(T obj);
}
