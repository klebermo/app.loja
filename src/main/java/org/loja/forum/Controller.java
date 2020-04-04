package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

public abstract class Controller<E extends org.loja.forum.Model> {
  @Autowired
  protected Service<E> serv;

  private Class<E> clazz;

  public Controller(Class<E> clazz) {
    this.clazz = clazz;
  }

  public Class<E> getClazz() {
    return this.clazz;
  }

  public void setClazz(Class<E> clazz) {
    this.clazz = clazz;
  }

  @RequestMapping(value="/view", method=RequestMethod.GET)
  public String view(Model model, @RequestParam("item") Integer id) {
    model.addAttribute(getClazz().getSimpleName().toLowerCase(), this.serv.get(id));
    return "index";
  }

  @RequestMapping(value="/save", method=RequestMethod.POST)
  public void save(@Valid E object, BindingResult result) {
    this.serv.set(object);
  }

  @RequestMapping(value="/new", method=RequestMethod.GET)
  public String newObject(Model model, @RequestParam("item") Integer id) throws InstantiationException, IllegalAccessException {
    model.addAttribute(getClazz().getSimpleName().toLowerCase(), this.serv.get(id));
    model.addAttribute("command", this.serv.newObject());
    return "index";
  }
}
