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

  public E insert(E object) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(object);
    entityManager.getTransaction().commit();
    entityManager.close();
    return object;
  }

  public E update(E object) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.merge(object);
    entityManager.getTransaction().commit();
    entityManager.close();
    return object;
  }

  public E delete(E object) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
    entityManager.getTransaction().commit();
    entityManager.close();
    return object;
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

  public List<E> search(String keyword) throws NoSuchFieldException {
    String sql = "SELECT a FROM "+clazz.getSimpleName()+" a";
    Field f = clazz.getDeclaredFields()[1];
    if(f.getType().getSuperclass() == org.loja.model.Model.class) {
      sql = sql + " INNER JOIN a."+f.getName()+" as b WHERE b."+f.getType().getDeclaredFields()[1].getName()+" LIKE '%"+keyword+"%'";
    } else if(f.getType() == List.class) {
      ParameterizedType listType = (ParameterizedType) f.getGenericType();
      Class<?> classElement = (Class<?>) listType.getActualTypeArguments()[0];
      sql = sql + " INNER JOIN a."+f.getName()+" as b WHERE b."+classElement.getDeclaredFields()[2].getName()+" LIKE '%"+keyword+"%'";
    } else if(f.getType() == String.class) {
      sql = sql + " WHERE "+f.getName().toLowerCase()+" LIKE '%"+keyword+"%'";
    } else {
      sql = sql + " WHERE "+f.getName().toLowerCase()+" = '"+keyword+"'";
    }
    System.out.println("sql: "+sql);

    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    List result = entityManager.createQuery(sql).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return result;
  }
}
