package org.loja.model.pedido;

import org.loja.model.Model;
import java.util.List;
import org.loja.model.produto.Produto;
import org.loja.model.cliente.Cliente;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;

@Entity
public class Pedido extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name="pedido_produtos", joinColumns={@JoinColumn(name="pedido_id")}, inverseJoinColumns={@JoinColumn(name="produto_id")})
  @OrderColumn
  private List<Produto> produtos;

  @Column
  private Date dataCompra;

  @Column
  private String metodoPagamento;

  @Column
  private String transactionId;

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

  public String toString() {
    return transactionId + " em " + dataCompra + " [" + produtos + "] via " + metodoPagamento;
  }
}
