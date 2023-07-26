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
import java.util.List;
import java.util.ArrayList;
import org.loja.model.cliente.Cliente;

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
  public void doRegister(@ModelAttribute("command") Usuario object, @ModelAttribute("cliente") Cliente cliente) throws Exception {
    this.serv.register(object, cliente);
  }

  @RequestMapping(value = "/credenciais", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_credencial')")
  public String formCredenciais(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", this.serv.findBy("id", id));
    model.addAttribute("credenciais", credenciais());
    model.addAttribute("autorizacoes", autorizacoes());
    return "admin/form/credenciais";
  }

  @RequestMapping(value = "/toggle_credencial", method=RequestMethod.POST)
  @ResponseBody
  public void toggle_credencial(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("credencial_id") Integer credencial_id) {
    this.serv.toggle_credencial(usuario_id, credencial_id);
  }

  @ModelAttribute("credenciais")
  public List<?> credenciais() {
    List<?> result;
    try {
      org.loja.model.credencial.CredencialService credencialServ = new org.loja.model.credencial.CredencialService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(credencialServ);
      result = credencialServ.select();
    } catch (Exception e) {
      result = new ArrayList<org.loja.model.credencial.Credencial>();
    }
    return result;
  }

  @ModelAttribute("autorizacoes")
  public List<?> autorizacoes() {
    List<?> result;
    try {
      org.loja.model.autorizacao.AutorizacaoService autorizacaoServ = new org.loja.model.autorizacao.AutorizacaoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(autorizacaoServ);
      result = (List<?>)autorizacaoServ.select();
    } catch (Exception e) {
      result = new ArrayList<org.loja.model.autorizacao.Autorizacao>();
    }
    return result;
  }
}
