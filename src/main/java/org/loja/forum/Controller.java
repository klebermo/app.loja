package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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

  public String getName() {
    return clazz.getSimpleName().toLowerCase();
  }

  @RequestMapping("/view/{item_id}")
  public String view(Model model, @PathVariable("item_id") Integer item_id) {
    model.addAttribute(this.getName(), this.serv.getData(item_id));
    return "index";
  }
}
