package org.loja.model.cesta;

import org.loja.model.Model;
import java.util.Set;
import org.loja.model.produto.Produto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Fetch;
import javax.persistence.FetchType;
import org.hibernate.annotations.FetchMode;

@Entity
public class Cesta extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private Set<Produto> produtos;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(Set<Produto> produtos) {
    this.produtos = produtos;
  }

  public String toString() {
    return produtos.toString();
  }
}
