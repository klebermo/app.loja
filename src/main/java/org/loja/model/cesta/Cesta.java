package org.loja.model.cesta;

import org.loja.model.Model;
import java.util.List;
import org.loja.model.produto.Produto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Cesta extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany
  private List<Produto> produtos;

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
