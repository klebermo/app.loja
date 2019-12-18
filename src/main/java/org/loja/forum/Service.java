package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class Service<E extends org.loja.model.Model> {
  private Class<E> clazz;

  @Autowired
  protected org.loja.model.Dao<E> dao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public Class<E> getClazz() {
    return this.clazz;
  }

  public void setClazz(Class<E> clazz) {
    this.clazz = clazz;
  }

  public E getData(Integer item_id) {
    return this.dao.findBy("id", item_id);
  }

  public List select() {
    return this.dao.select();
  }
}
