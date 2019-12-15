package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

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
    List result = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" = :value").setParameter("value", value).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    if(result.isEmpty())
      return null;
    else
      return (E) result.get(0);
  }

  public List<E> search(String key, Object value) throws NoSuchFieldException {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();

    List result;
    Field field = clazz.getDeclaredField(key);

    ParameterizedType listType = (ParameterizedType) field.getGenericType();
    Class<?> classElement = (Class<?>) listType.getActualTypeArguments()[0];
    String nome = classElement.getSimpleName();

    Field field2[] = classElement.getDeclaredFields();
    String attr = field2[field2.length - 1].getName();

    if(field != null) {
      if(field2 != null) {
        System.out.println("SELECT a FROM "+nome+" a WHERE a."+attr+" LIKE '"+value+"%'");
        result = entityManager.createQuery("SELECT a FROM "+nome+" a WHERE a."+attr+" LIKE '"+value+"%'").getResultList();
      } else {
        System.out.println("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" LIKE '"+value+"%'");
        result = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" LIKE '"+value+"%'").getResultList();
      }
    } else {
      System.out.println("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" LIKE '"+value+"%'");
      result = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+key+" LIKE '"+value+"%'").getResultList();
    }

    entityManager.getTransaction().commit();
    entityManager.close();
    return result;
  }
}
