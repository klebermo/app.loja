package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class Controller<E> {
  @Autowired
  protected Service<E> serv;

  private Class<E> clazz;

  public Controller(Class<E> clazz) {
    this.clazz = clazz;
  }

  @RequestMapping(value = "/get")
  public String get(Model model) {
    model.addAttribute("command", serv.get());
    return "admin/index";
  }

  @RequestMapping(value = "/set", method=RequestMethod.POST)
  @ResponseBody
  public void set(@Valid E object, BindingResult result) {
    serv.set(object);
  }

  @ModelAttribute("setting")
  public Class<E> getClazz() {
    return clazz;
  }

  @ModelAttribute("target")
  public String getName() {
    return clazz.getSimpleName().toLowerCase();
  }
}
