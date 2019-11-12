package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.loja.model.credencial.CredencialDao;
import org.loja.model.credencial.Credencial;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.mail.MessagingException;
import org.loja.MailSender;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  @Autowired
  private UsuarioDao dao;

  @Autowired
  private CredencialDao credencialDao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private ProdutoDao produtoDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private MailSender mailServer;

  @Autowired
  private HttpServletRequest httpServletRequest;

  public UsuarioService() {
    super(Usuario.class);
  }

  public Boolean register(Usuario novo) throws MessagingException {
    mailServer.send_mail(novo.getEmail(), novo.getFirstName(), novo.getLastName(), "Confirmação de cadastro", "...");
    novo.setEnabled(true);
    novo.setLocked(false);
    this.dao.insert(novo);
    return true;
  }

  public String recoverPassword(String email, String token) throws MessagingException {
    Usuario usuario = this.dao.findBy("email", email);
    if(usuario != null)
      if(token == null) {
        token = UUID.randomUUID().toString();
        mailServer.send_mail(usuario.getEmail(), usuario.getFirstName(), usuario.getLastName(), "Recuperação de senha", "...");
        return token;
      } else {
        mailServer.send_mail(usuario.getEmail(), usuario.getFirstName(), usuario.getLastName(), "Nova senha", "...");
        return null;
      }
    else
      return null;
  }

  public void toggle_credencial(Integer usuario_id, Integer credencial_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Credencial credencial = credencialDao.findBy("id", credencial_id);
    if(usuario.getCredenciais() == null) {
      usuario.setCredenciais(new ArrayList<Credencial>());
      usuario.getCredenciais().add(credencial);
      this.dao.update(usuario);
    } else {
      if(usuario.getCredenciais().contains(credencial)) {
        usuario.getCredenciais().remove(credencial);
        this.dao.update(usuario);
      } else {
        usuario.getCredenciais().add(credencial);
        this.dao.update(usuario);
      }
    }
  }

  public Integer cart_size(Integer usuario_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null || usuario.getCesta().getProdutos() == null)
      return 0;
    return usuario.getCesta().getProdutos().size();
  }

  public Float cart_total(Integer usuario_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null || usuario.getCesta().getProdutos() == null)
      return 0.f;
    Float total = 0.f;
    for(Produto produto : usuario.getCesta().getProdutos())
      total += produto.getPreco();
    return total;
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
      cesta.setProdutos(new ArrayList<Produto>());
      cestaDao.update(cesta);
    }
    Produto produto = produtoDao.findBy("id", produto_id);
    cesta.getProdutos().add(produto);
    cestaDao.update(cesta);
  }

  public void remove_from_cart(Integer usuario_id, Integer produto_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Cesta cesta = usuario.getCesta();
    for(Produto produto : cesta.getProdutos()) {
      if(produto.getId() == produto_id) {
        cesta.getProdutos().remove(produto);
        cestaDao.update(cesta);
        break;
      }
    }
  }
}
