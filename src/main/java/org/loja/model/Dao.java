package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;

public abstract class Dao<E> {
  @Autowired
  private EntityManagerFactory factory;

  private Class<E> clazz;

  public Dao(Class<E> clazz) {
    this.clazz = clazz;
  }

  public EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  public Result insert(E object) {
    try {
      EntityManager entityManager = getEntityManager();
  		entityManager.getTransaction().begin();
  		entityManager.persist(object);
  		entityManager.getTransaction().commit();
      entityManager.close();
      return new Result(false, clazz.getSimpleName().toLowerCase(), "insert", ((Model)object).getId(), null, null);
    } catch (Exception e) {
      e.printStackTrace();
      return new Result(true, clazz.getSimpleName().toLowerCase(), "insert", null, e.toString(), e.getStackTrace());
    }
  }

  public Result update(E object) {
    try {
      EntityManager entityManager = getEntityManager();
  		entityManager.getTransaction().begin();
  		E result = entityManager.merge(object);
  		entityManager.getTransaction().commit();
      entityManager.close();
      return new Result(false, clazz.getSimpleName().toLowerCase(), "update", ((Model)result).getId(), null, null);
    }catch (Exception e) {
      e.printStackTrace();
      return new Result(true, clazz.getSimpleName().toLowerCase(), "update", null, e.toString(), e.getStackTrace());
    }
  }

  public Result delete(E object) {
    try {
      EntityManager entityManager = getEntityManager();
  		entityManager.getTransaction().begin();
  		entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
  		entityManager.getTransaction().commit();
      entityManager.close();
      return new Result(false, clazz.getSimpleName().toLowerCase(), "delete", ((Model)object).getId(), null, null);
    } catch(Exception e) {
      e.printStackTrace();
      return new Result(true, clazz.getSimpleName().toLowerCase(), "delete", null, e.toString(), e.getStackTrace());
    }
  }

  public List<E> select() {
    EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<E> lista = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
    return lista;
  }

  public E findBy(String key, Object value) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    List<E> lista = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" = :value").setParameter("value", value).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();

    if(!lista.isEmpty())
      return (E) lista.get(0);
    else
      return null;
  }
}
