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
import javax.persistence.OneToOne;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;
import org.loja.model.registro.Registro;

@Entity
public class Pedido extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name="pedido_produtos", joinColumns={@JoinColumn(name="pedido_id")}, inverseJoinColumns={@JoinColumn(name="produto_id")})
  @OrderColumn
  private List<Produto> produtos;

  @Column(name="data_compra")
  private Date dataCompra;

  @Column(name="metodo_pagamento")
  private String metodoPagamento;

  @Column(name="transaction_id")
  private String transactionId;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Registro registro;


	/**
	* Default empty Pedido constructor
	*/
	public Pedido() {
		super();
	}

	/**
	* Default Pedido constructor
	*/
	public Pedido(Integer id, List<Produto> produtos, Date dataCompra, String metodoPagamento, String transactionId, Registro registro) {
		super();
		this.id = id;
		this.produtos = produtos;
		this.dataCompra = dataCompra;
		this.metodoPagamento = metodoPagamento;
		this.transactionId = transactionId;
		this.registro = registro;
	}

	/**
	* Returns value of id
	* @return
	*/
	public Integer getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	* Returns value of produtos
	* @return
	*/
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	* Sets new value of produtos
	* @param
	*/
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	* Returns value of dataCompra
	* @return
	*/
	public Date getDataCompra() {
		return dataCompra;
	}

	/**
	* Sets new value of dataCompra
	* @param
	*/
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	/**
	* Returns value of metodoPagamento
	* @return
	*/
	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	/**
	* Sets new value of metodoPagamento
	* @param
	*/
	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	/**
	* Returns value of transactionId
	* @return
	*/
	public String getTransactionId() {
		return transactionId;
	}

	/**
	* Sets new value of transactionId
	* @param
	*/
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	* Returns value of registro
	* @return
	*/
	public Registro getRegistro() {
		return registro;
	}

	/**
	* Sets new value of registro
	* @param
	*/
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	/**
	* Create string representation of Pedido for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", produtos=" + produtos + ", dataCompra=" + dataCompra + ", metodoPagamento=" + metodoPagamento + ", transactionId=" + transactionId + ", registro=" + registro + "]";
	}
}
