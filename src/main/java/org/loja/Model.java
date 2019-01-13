package org.loja;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import org.loja.model.categoria.Categoria;
import org.loja.model.produto.Produto;
import org.loja.model.usuario.Usuario;
import org.loja.model.credencial.Credencial;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.produto.ProdutoService;
import org.loja.model.usuario.UsuarioService;
import org.loja.model.credencial.CredencialService;

@ControllerAdvice
public class Model {
  @Autowired
  private CategoriaService categoria;

  @Autowired
  private ProdutoService produto;

  @Autowired
  private CredencialService credencial;

  @ModelAttribute("categorias")
  public List<Categoria> categorias() {
    return categoria.select();
  }

  @ModelAttribute("produtos")
  public List<Produto> produtos() {
    return produto.select();
  }

  @ModelAttribute("credenciais")
  public List<Credencial> credenciais() {
    return credencial.select();
  }
}
