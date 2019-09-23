package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class Controller<E> {
  private Class<E> clazz;

  @Autowired
  protected Service<E> serv;

  public Controller(Class<E> clazz) {
    this.clazz = clazz;
  }

  @RequestMapping("/")
  @PreAuthorize("hasPermission(#user, 'consulta_'+#this.this.name)")
  public String index(Model model) {
    return "admin";
  }

  @RequestMapping(value = "/insert", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'cadastra_'+#this.this.name)")
  public String insert(Model model) throws InstantiationException, IllegalAccessException {
    model.addAttribute("command", serv.newObject());
    return "form/form_insert";
  }

  @RequestMapping(value = "/insert", method=RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasPermission(#user, 'cadastra_'+#this.this.name)")
  public void insert(@Valid E object, BindingResult result) {
    serv.insert(object);
  }

  @RequestMapping(value = "/update", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'atualiza_'+#this.this.name)")
  public String update(Model model, @RequestParam(value="id") String id) {
    model.addAttribute("command", serv.findBy("id", new Integer(id)));
    return "form/form_update";
  }

  @RequestMapping(value = "/update", method=RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasPermission(#user, 'atualiza_'+#this.this.name)")
  public void update(@Valid E object, BindingResult result) {
    serv.update(object);
  }

  @RequestMapping(value = "/delete", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'remove_'+#this.this.name)")
  public String delete(Model model, @RequestParam(value="id") String id) {
    model.addAttribute("command", serv.findBy("id", new Integer(id)));
    return "form/form_delete";
  }

  @RequestMapping(value = "/delete", method=RequestMethod.DELETE)
  @ResponseBody
  @PreAuthorize("hasPermission(#user, 'remove_'+#this.this.name)")
  public void delete(@Valid E object, BindingResult result) {
    serv.delete(object);
  }

  @RequestMapping("/list.json")
  @ResponseBody
  public String list() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(serv.select());
  }

  @ModelAttribute("classe")
  public Class<E> getClazz() {
    return clazz;
  }

  public String getName() {
    return clazz.getSimpleName().toLowerCase();
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(org.loja.model.arquivo.Arquivo.class, new org.loja.model.arquivo.ArquivoEditor());
    binder.registerCustomEditor(org.loja.model.autorizacao.Autorizacao.class, new org.loja.model.autorizacao.AutorizacaoEditor());
    binder.registerCustomEditor(org.loja.model.categoria.Categoria.class, new org.loja.model.categoria.CategoriaEditor());
    binder.registerCustomEditor(org.loja.model.cesta.Cesta.class, new org.loja.model.cesta.CestaEditor());
    binder.registerCustomEditor(org.loja.model.credencial.Credencial.class, new org.loja.model.credencial.CredencialEditor());
    binder.registerCustomEditor(org.loja.model.imagem.Imagem.class, new org.loja.model.imagem.ImagemEditor());
    binder.registerCustomEditor(org.loja.model.pedido.Pedido.class, new org.loja.model.pedido.PedidoEditor());
    binder.registerCustomEditor(org.loja.model.produto.Produto.class, new org.loja.model.produto.ProdutoEditor());
    binder.registerCustomEditor(org.loja.model.pagina.Pagina.class, new org.loja.model.pagina.PaginaEditor());
    binder.registerCustomEditor(org.loja.model.usuario.Usuario.class, new org.loja.model.usuario.UsuarioEditor());
    binder.registerCustomEditor(java.util.Date.class, new org.loja.model.usuario.DateEditor());
    binder.registerCustomEditor(java.lang.Float.class, new org.loja.model.produto.FloatEditor());
  }
}
