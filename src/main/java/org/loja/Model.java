package org.loja;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import org.loja.model.categoria.Categoria;
import org.loja.model.produto.Produto;
//import org.loja.model.usuario.Usuario;
import org.loja.model.credencial.Credencial;
import org.loja.settings.geral.Geral;
import org.loja.settings.paypal.Paypal;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.produto.ProdutoService;
//import org.loja.model.usuario.UsuarioService;
import org.loja.model.credencial.CredencialService;
import org.loja.settings.geral.GeralService;
import org.loja.settings.paypal.PaypalService;
//import org.springframework.security.core.context.SecurityContextHolder;

@ControllerAdvice
public class Model {
  @Autowired
  private CategoriaService categoria;

  @Autowired
  private ProdutoService produto;

  /*@Autowired
  private UsuarioService usuario;*/

  @Autowired
  private CredencialService credencial;

  @Autowired
  private GeralService geral;

  @Autowired
  private PaypalService paypal;

  @ModelAttribute("categorias")
  public List<Categoria> categorias() {
    return categoria.select();
  }

  @ModelAttribute("produtos")
  public List<Produto> produtos() {
    return produto.select();
  }

  /*@ModelAttribute("usuario")
  public Usuario usuario() {
    return usuario.findBy("username", SecurityContextHolder.getContext().getAuthentication().getName());
  }*/

  @ModelAttribute("credenciais")
  public List<Credencial> credenciais() {
    return credencial.select();
  }

  @ModelAttribute("geral")
  public Geral geral() {
    return geral.get();
  }

  @ModelAttribute("paypal")
  public Paypal paypal() {
    return paypal.get();
  }
}
