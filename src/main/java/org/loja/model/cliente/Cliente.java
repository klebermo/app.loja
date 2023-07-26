package org.loja.model.cliente;

import javax.persistence.Entity;
import org.loja.model.Model;
import java.io.Serializable;
import javax.persistence.Id;
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
import java.util.UUID;
import org.loja.model.produto.Produto;

@Entity
public class Cliente extends Model implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Cesta cesta;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderColumn
  private List<Pedido> pedidos;

	/**
	* Default empty Cliente constructor
	*/
	public Cliente() {
    this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

  /**
	* Default Cliente constructor
	*/
	public Cliente(Usuario usuario, Cesta cesta, List<Pedido> pedidos) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.usuario = usuario;
		this.cesta = cesta;
		this.pedidos = pedidos;
	}

	/**
	* Default Cliente constructor
	*/
	public Cliente(String id, Usuario usuario, Cesta cesta, List<Pedido> pedidos) {
		this.id = id;
		this.usuario = usuario;
		this.cesta = cesta;
		this.pedidos = pedidos;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of usuario
	* @return
	*/
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	* Sets new value of usuario
	* @param
	*/
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	* Returns value of cesta
	* @return
	*/
	public Cesta getCesta() {
		return cesta;
	}

	/**
	* Sets new value of cesta
	* @param
	*/
	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}

	/**
	* Returns value of pedidos
	* @return
	*/
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	/**
	* Sets new value of pedidos
	* @param
	*/
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
