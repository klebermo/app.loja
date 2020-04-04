package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class Dao<E extends org.loja.forum.Model> {
  @Autowired
  private EntityManagerFactory factory;

  private Class<E> clazz;

  public Dao(Class<E> clazz) {
    this.clazz = clazz;
  }

  public EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  public List<E> all() {
    EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<E> lista = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
    return lista;
  }

  public E get(Integer id) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    List result = entityManager.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a WHERE a.id = :value").setParameter("value", id).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    if(result.isEmpty())
      return null;
    else
      return (E) result.get(0);
  }

  public void set(E object) {
    EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(object);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
