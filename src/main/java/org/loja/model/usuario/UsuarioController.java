package org.loja.model.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends org.loja.model.Controller<Usuario> {
  public UsuarioController() {
    super(Usuario.class);
  }

  @Autowired
  private UsuarioService serv;

  @RequestMapping(value = "/register", method=RequestMethod.GET)
  public String formRegister(Model model) {
    model.addAttribute("register", "register");
    model.addAttribute("command", new Usuario());
    return "register";
  }

  @RequestMapping(value = "/register", method=RequestMethod.POST)
  @ResponseBody
  public void doRegister(@ModelAttribute("command") Usuario object) throws Exception {
    this.serv.register(object);
  }

  @RequestMapping(value = "/recoverPassword", method=RequestMethod.GET)
  public String formRecover(Model model) {
    model.addAttribute("recover", "recover");
    return "recover";
  }

  @RequestMapping(value = "/recoverPassword", method=RequestMethod.POST)
  @ResponseBody
  public void doRecover(@RequestParam("email") String email) throws Exception {
    this.serv.recoverPassword(email);
  }

  @RequestMapping(value = "/credenciais", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_credencial')")
  public String formCredenciais(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", this.serv.findBy("id", id));
    return "admin/form/credenciais";
  }

  @RequestMapping(value = "/toggle_credencial", method=RequestMethod.POST)
  @ResponseBody
  public void toggle_credencial(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("credencial_id") Integer credencial_id) {
    this.serv.toggle_credencial(usuario_id, credencial_id);
  }
}
