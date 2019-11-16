package org.loja.model.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
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
    model.addAttribute("command", new Usuario());
    return "register";
  }

  @RequestMapping(value = "/register", method=RequestMethod.POST)
  @ResponseBody
  public void doRegister(@Valid Usuario object, BindingResult result) throws Exception {
    this.serv.register(object);
  }

  @RequestMapping(value = "/recoverPassword", method=RequestMethod.GET)
  public String formRecover() {
    return "recover";
  }

  @RequestMapping(value = "/recoverPassword", method=RequestMethod.POST)
  public String doRecoverPassword(@RequestParam("email") String email, @RequestParam(value="token", required=false) String token) throws Exception {
    String result = this.serv.recoverPassword(email, token);
    if(result == null)
      return "redirect:/login";
    else
      return "redirect:/recoverPassword?email="+email+"&token="+result;
  }

  @RequestMapping(value = "/credenciais", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_'+#this.this.name)")
  public String formCredenciais(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", serv.findBy("id", id));
    return "admin/form/credenciais";
  }

  @RequestMapping(value = "/toggle_credencial", method=RequestMethod.POST)
  @ResponseBody
  public void toggle_credencial(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("credencial_id") Integer credencial_id) {
    this.serv.toggle_credencial(usuario_id, credencial_id);
  }

  @RequestMapping(value = "/cart_size", method=RequestMethod.POST)
  @ResponseBody
  public Integer cart_size(@RequestParam("usuario") Integer usuario_id) {
    return this.serv.cart_size(usuario_id);
  }

  @RequestMapping(value = "/cart_total", method=RequestMethod.POST)
  @ResponseBody
  public Float cart_total(@RequestParam("usuario") Integer usuario_id) {
    return this.serv.cart_total(usuario_id);
  }

  @RequestMapping(value = "/add_to_cart", method=RequestMethod.POST)
  @ResponseBody
  public void add_to_cart(@RequestParam("usuario") Integer usuario_id, @RequestParam("produto") Integer produto_id) {
    this.serv.add_to_cart(usuario_id, produto_id);
  }

  @RequestMapping(value = "/remove_from_cart", method=RequestMethod.POST)
  @ResponseBody
  public void remove_from_cart(@RequestParam("usuario") Integer usuario_id, @RequestParam("produto") Integer produto_id) {
    this.serv.remove_from_cart(usuario_id, produto_id);
  }
}
