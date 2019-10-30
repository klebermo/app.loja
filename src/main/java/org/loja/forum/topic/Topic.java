package org.loja.forum.topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import org.loja.model.usuario.Usuario;

@Entity
public class Topic extends org.loja.model.Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(length=32)
  private String titulo;

  @Column(length=2097152)
  private String descricao;

  @Column
  private Date dataPublicacao;

  @OneToOne(fetch = FetchType.EAGER)
  private Usuario autor;

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

  public Usuario getAutor() {
    return autor;
  }

  public void setAutor(Usuario autor) {
    this.autor = autor;
  }

  @Override
  public String toString() {
    return titulo;
  }
}