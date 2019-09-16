package org.loja.model.pagina;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

@Entity
public class Pagina extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String titulo;

  @Column(length=2097152)
  private String descricao;

  @OneToOne(fetch = FetchType.EAGER)
  private Pagina parent;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String nome) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Pagina getParent() {
    return parent;
  }

  public void setParent(Pagina parent) {
    this.parent = parent;
  }

  public String toString() {
    return titulo;
  }
}
