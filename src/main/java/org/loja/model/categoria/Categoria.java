package org.loja.model.categoria;

import org.loja.model.Model;
import org.loja.model.imagem.Imagem;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Categoria extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

  @Column
  private String resumo;

  @OneToOne
  private Imagem icone;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getResumo() {
    return resumo;
  }

  public void setResumo(String resumo) {
    this.resumo = resumo;
  }

  public Imagem getIcone() {
    return icone;
  }

  public void setIcone(Imagem icone) {
    this.icone = icone;
  }

  public String toString() {
    return nome;
  }
}
