package org.loja.model.cliente;

import javax.persistence.Entity;
import org.loja.model.Model;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;
import org.loja.model.usuario.Usuario;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.Pedido;
import java.util.List;
import java.util.ArrayList;
import org.loja.model.produto.Produto;

@Entity
public class Cliente extends Model implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Cesta cesta;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderColumn
  private List<Pedido> pedidos;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Cesta getCesta() {
    return cesta;
  }

  public void setCesta(Cesta cesta) {
    this.cesta = cesta;
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  @Override
  public String toString() {
    return usuario.toString();
  }

  public List<Produto> produtosComprados() {
    List<Produto> result = new ArrayList<Produto>();

    for(Pedido pedido : pedidos) {
      result.addAll(pedido.getProdutos());
    }

    return result;
  }
}
