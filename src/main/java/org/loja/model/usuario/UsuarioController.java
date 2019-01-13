package org.loja.model.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends org.loja.model.Controller<Usuario> {
  @Autowired
  private UsuarioService serv;

  public UsuarioController() {
    super(Usuario.class);
  }

  @RequestMapping(value = "/register", method=RequestMethod.GET)
  public String formRegister(Model model) {
    model.addAttribute("comand", new Usuario());
    return "register";
  }

  @RequestMapping(value = "/register", method=RequestMethod.POST)
  public String doRegister(@Valid Usuario object, BindingResult result) {
      this.serv.register(object);
      return "redirect:/";
  }

  @RequestMapping(value = "/credenciais", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_'+#this.this.name)")
  public String formCredenciais(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("currentUser", id);
    model.addAttribute("credenciaisUser", serv.findBy("id", id).getCredenciais());
    return "form_credenciais";
  }
}
