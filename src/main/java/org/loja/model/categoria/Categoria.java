package org.loja.model.categoria;

import org.loja.model.Model;
import org.loja.model.imagem.Imagem;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.loja.model.titulo.Titulo;
import org.loja.model.resumo.Resumo;

@Entity
public class Categoria extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
  @OrderColumn
  private List<Titulo> nome;

  @OneToOne(fetch = FetchType.EAGER)
  private Imagem icone;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Titulo> getNome() {
    return nome;
  }

  public void setNome(List<Titulo> nome) {
    this.nome = nome;
  }

  public Imagem getIcone() {
    return icone;
  }

  public void setIcone(Imagem icone) {
    this.icone = icone;
  }

  public String toString() {
    return nome.toString();
  }
}
