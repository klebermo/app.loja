package org.loja.model.cesta;

import org.loja.model.Model;
import java.util.List;
import java.util.ArrayList;
import org.loja.model.produto.Produto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;

@Entity
public class Cesta extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name="cesta_produtos", joinColumns={@JoinColumn(name="cesta_id")}, inverseJoinColumns={@JoinColumn(name="produto_id")})
  @OrderColumn
  private List<Produto> produtos;

  /**
	* Default empty Cesta constructor
	*/
	public Cesta() {
		super();
    setProdutos(new ArrayList<Produto>());
	}

	/**
	* Default Cesta constructor
	*/
	public Cesta(Integer id, List<Produto> produtos) {
		super();
		this.id = id;
		this.produtos = produtos;
	}

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

  public String toString() {
    return produtos.toString();
  }
}
