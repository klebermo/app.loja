package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Service<E> {
  @Autowired
  protected Dao<E> dao;

  public Class<E> clazz;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public E get() {
    return dao.get();
  }

  public E set(E object) {
    return dao.set(object);
  }
}
