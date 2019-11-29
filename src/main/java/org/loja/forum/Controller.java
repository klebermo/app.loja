package org.loja.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.loja.model.produto.ProdutoService;
import org.loja.model.produto.Produto;
import org.loja.model.usuario.UsuarioService;
import org.loja.model.usuario.Usuario;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class Controller<E extends org.loja.model.Model> {
  private Class<E> clazz;

  @Autowired
  protected Service<E> serv;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private UsuarioService usuarioService;

  public Controller(Class<E> clazz) {
    this.clazz = clazz;
  }

  @ModelAttribute("classe")
  public Class<E> getClazz() {
    return this.clazz;
  }

  public void setClazz(Class<E> clazz) {
    this.clazz = clazz;
  }

  @ModelAttribute("target")
  public String getName() {
    return clazz.getSimpleName().toLowerCase();
  }

  @RequestMapping("/view/{item_id}")
  public String view(Model model, @PathVariable("item_id") Integer item_id, @RequestParam(value="page", required=false, defaultValue="1") Integer page) {
    model.addAttribute(this.getName(), this.serv.getData(item_id));
    model.addAttribute("page", page);
    return "index";
  }

  @ModelAttribute("produtos")
  public List<Produto> produtos() {
    return produtoService.select();
  }

  @ModelAttribute("usuario")
  public Usuario usuario() {
    return usuarioService.findBy("username", SecurityContextHolder.getContext().getAuthentication().getName());
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(java.util.Date.class, new org.loja.model.usuario.DateEditor());
    binder.registerCustomEditor(org.loja.forum.forum.Forum.class, new org.loja.forum.forum.ForumEditor());
    binder.registerCustomEditor(org.loja.forum.topic.Topic.class, new org.loja.forum.topic.TopicEditor());
  }
}
