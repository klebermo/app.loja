package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Service<E> {
  protected Class<E> clazz;

  @Autowired
  protected Dao<E> dao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public E insert(E object) {
    return dao.insert(object);
  }

  public E update(E object) {
    return dao.update(object);
  }

  public E delete(E object) {
    return dao.delete(object);
  }

  public List<?> select() {
    return this.dao.select();
  }

  public Object findBy(String key, Object value) {
    return this.dao.findBy(key, value);
  }

  public Object newObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
    return (Object)clazz.getDeclaredConstructors()[0].newInstance();
  }
}
