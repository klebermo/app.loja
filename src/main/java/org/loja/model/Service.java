package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class Service<E> {
  protected Class<E> clazz;

  @Autowired
  protected Dao<E> dao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public void insert(E object) {
    dao.insert(object);
  }

  public void update(E object) {
    dao.update(object);
  }

  public void delete(E object) {
    dao.delete(object);
  }

  public List<E> select() {
    return dao.select();
  }

  public E findBy(String key, Object value) {
    return dao.findBy(key, value);
  }

  public E newObject() throws InstantiationException, IllegalAccessException {
    return clazz.newInstance();
  }
}
