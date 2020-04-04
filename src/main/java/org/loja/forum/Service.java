package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class Service<E extends org.loja.forum.Model> {
  @Autowired
  protected org.loja.forum.Dao<E> dao;

  private Class<E> clazz;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public Class<E> getClazz() {
    return this.clazz;
  }

  public void setClazz(Class<E> clazz) {
    this.clazz = clazz;
  }

  public List<E> all() {
    return this.dao.all();
  }

  public E get(Integer id) {
    return this.dao.get(id);
  }

  public void set(E object) {
    this.dao.set(object);
  }

  public E newObject() throws InstantiationException, IllegalAccessException {
    return clazz.newInstance();
  }
}
