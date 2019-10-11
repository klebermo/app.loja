package org.loja.model.categoria;

import org.loja.model.Model;
import org.loja.model.imagem.Imagem;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
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

  @OneToMany
  private List<Titulo> nome;

  @OneToMany
  private List<Resumo> resumo;

  @OneToOne
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

  public List<Resumo> getResumo() {
    return resumo;
  }

  public void setResumo(List<Resumo> resumo) {
    this.resumo = resumo;
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
