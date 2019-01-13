package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Service<E> {
  private Dao<E> dao;

  private Class<E> clazz;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public E get() {
    return dao.get();
  }

  public void set(E object) {
    dao.set(object);
  }
}
