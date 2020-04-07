package org.loja.model.resposta;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;

@Entity
public class Resposta extends org.loja.model.Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(length=140)
  private String titulo;

  @Column(length=2097152)
  private String descricao;

  @Column
  private Date dataPublicacao;

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

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataPublicacao() {
    return dataPublicacao;
  }

  public void setDataPublicacao(Date dataPublicacao) {
    this.dataPublicacao = dataPublicacao;
  }

  @Override
  public String toString() {
    return titulo;
  }
}
