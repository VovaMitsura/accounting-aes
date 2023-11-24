package com.example.dal.config.util;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class EntityManagerUtil {

  private final SessionFactory entityManagerFactory;

  public EntityManagerUtil(@Autowired SessionFactory entityFactory) {
    this.entityManagerFactory = entityFactory;
  }

  public void performWithinTx(Consumer<EntityManager> entityManagerConsumer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    try {
      entityManagerConsumer.accept(entityManager);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      throw e;
    } finally {
      entityManager.close();
    }
  }

  public <T> T performReturningWithinTx(Function<EntityManager, T> entityManagerFunction) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    try {
      T result = entityManagerFunction.apply(entityManager);
      entityManager.getTransaction().commit();
      return result;
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
