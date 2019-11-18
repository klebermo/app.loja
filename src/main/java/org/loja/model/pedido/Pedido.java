package org.loja.model.pedido;

import org.loja.model.Model;
import java.util.List;
import org.loja.model.produto.Produto;
import org.loja.model.usuario.Usuario;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Fetch;
import javax.persistence.FetchType;
import org.hibernate.annotations.FetchMode;

@Entity
public class Pedido extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="pedido_produtos", joinColumns={@JoinColumn(name="pedido_id")}, inverseJoinColumns={@JoinColumn(name="produto_id")})
  @Fetch(FetchMode.SELECT)
  private List<Produto> produtos;

  @Column
  private Date dataCompra;

  @Column
  private String metodoPagamento;

  @Column
  private String transactionId;

  @ManyToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private Usuario usuario;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  public Date getDataCompra() {
    return dataCompra;
  }

  public void setDataCompra(Date dataCompra) {
    this.dataCompra = dataCompra;
  }

  public String getMetodoPagamento() {
    return metodoPagamento;
  }

  public void setMetodoPagamento(String metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String toString() {
    return transactionId + " em " + dataCompra + " [" + produtos.toString() + "] via " + metodoPagamento;
  }
}
