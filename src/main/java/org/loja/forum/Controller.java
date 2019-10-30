package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public abstract class Controller<E extends org.loja.model.Model> {
  private Class<E> clazz;

  @Autowired
  protected Service<E> serv;

  public Controller(Class<E> clazz) {
    this.clazz = clazz;
  }

  public Class<E> getClazz() {
    return this.clazz;
  }

  public void setClazz(Class<E> clazz) {
    this.clazz = clazz;
  }

  public abstract String view(Model model, Integer item_id);
}
