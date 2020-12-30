package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

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

  public List<E> select() {
    return this.dao.select();
  }

  public E findBy(String key, Object value) {
    return this.dao.findBy(key, value);
  }

  public E newObject() throws InstantiationException, IllegalAccessException {
    return clazz.newInstance();
  }
}
