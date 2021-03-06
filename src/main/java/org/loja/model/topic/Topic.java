package org.loja.model.topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import org.loja.model.usuario.Usuario;
import org.loja.model.forum.Forum;
import org.loja.model.resposta.Resposta;

@Entity
public class Topic extends org.loja.model.Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(length=140)
  private String titulo;

  @Column(length=2097152)
  private String descricao;

  @Column(name="data_publicacao")
  private Date dataPublicacao;

  @OneToOne(fetch = FetchType.EAGER)
  private Usuario autor;

  @OneToOne(fetch = FetchType.EAGER)
  private Resposta resposta;

  @ManyToOne(fetch = FetchType.EAGER)
  private Forum forum;

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

  public Resposta getResposta() {
    return resposta;
  }

  public void setResposta(Resposta resposta) {
    this.resposta = resposta;
  }

  public Forum getForum() {
    return forum;
  }

  public void setForum(Forum forum) {
    this.forum = forum;
  }

  @Override
  public String toString() {
    return titulo;
  }
}
