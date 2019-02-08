package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import java.util.HashSet;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  @Autowired
  private UsuarioDao dao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private ProdutoDao produtoDao;

  public UsuarioService() {
    super(Usuario.class);
  }

  public void register(Usuario novo) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
    String senha = bcrypt.encode(novo.getPassword());
    novo.setPassword(senha);
    novo.setEnabled(true);
    this.dao.insert(novo);
  }

  public void add_to_cart(Integer usuario_id, Integer produto_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null) {
      Cesta cesta = new Cesta();
      cestaDao.insert(cesta);
      usuario.setCesta(cesta);
      this.dao.update(usuario);
    }
    Cesta cesta = usuario.getCesta();
    if(cesta.getProdutos() == null) {
      cesta.setProdutos(new HashSet<Produto>());
      cestaDao.update(cesta);
    }
    Produto produto = produtoDao.findBy("id", produto_id);
    cesta.getProdutos().add(produto);
    cestaDao.update(cesta);
  }

  public void remove_from_cart(Integer usuario_id, Integer produto_id) {
    //
  }

  public void checkout(Integer usuario_id) {
    //
  }
}
