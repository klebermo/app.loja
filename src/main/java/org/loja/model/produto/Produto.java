package org.loja.model.produto;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import org.loja.model.titulo.Titulo;
import org.loja.model.resumo.Resumo;
import org.loja.model.texto.Texto;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.OrderColumn;
import org.loja.model.categoria.Categoria;
import javax.persistence.Column;
import org.loja.model.imagem.Imagem;
import org.loja.model.arquivo.Arquivo;
import java.util.List;
import java.util.ArrayList;
import org.loja.model.forum.Forum;

@Entity
public class Produto extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

  @OneToMany(fetch = javax.persistence.FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
  @OrderColumn
  private List<Resumo> resumo;

  @OneToMany(fetch = javax.persistence.FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
  @OrderColumn
  private List<Texto> descricao;

  @OneToOne
  @Fetch(FetchMode.JOIN)
  private Categoria categoria;

  @Column
  private Float preco;

  @OneToOne
  @Fetch(FetchMode.JOIN)
  private Imagem icone;

  @OneToMany
  @Fetch(FetchMode.JOIN)
  @Cascade(CascadeType.SAVE_UPDATE)
  @OrderColumn
  private List<Imagem> thumbnails;

  @OneToMany
  @Fetch(FetchMode.JOIN)
  @Cascade(CascadeType.SAVE_UPDATE)
  @OrderColumn
  private List<Arquivo> versaoGratuita;

  @OneToMany
  @Fetch(FetchMode.JOIN)
  @Cascade(CascadeType.SAVE_UPDATE)
  @OrderColumn
  private List<Arquivo> versaoPaga;

  @OneToOne
  @Fetch(FetchMode.JOIN)
  private Forum forum;

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

  public List<Resumo> getResumo() {
    return resumo;
  }

  public void setResumo(List<Resumo> resumo) {
    this.resumo = resumo;
  }

  public List<Texto> getDescricao() {
    return descricao;
  }

  public void setDescricao(List<Texto> descricao) {
    this.descricao = descricao;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Float getPreco() {
    return preco;
  }

  public void setPreco(Float preco) {
    this.preco = preco;
  }

  public Imagem getIcone() {
    return icone;
  }

  public void setIcone(Imagem icone) {
    this.icone = icone;
  }

  public List<Imagem> getThumbnails() {
    return thumbnails;
  }

  public void setThumbnails(List<Imagem> thumbnails) {
    this.thumbnails = thumbnails;
  }

  public List<Arquivo> getVersaoGratuita() {
    return versaoGratuita;
  }

  public void setVersaoGratuita(List<Arquivo> versaoGratuita) {
    this.versaoGratuita = versaoGratuita;
  }

  public List<Arquivo> getVersaoPaga() {
    return versaoPaga;
  }

  public void setVersaoPaga(List<Arquivo> versaoPaga) {
    this.versaoPaga = versaoPaga;
  }

  public Forum getForum() {
    return forum;
  }

  public void setForum(Forum forum) {
    this.forum = forum;
  }

  public String toString() {
    return nome;
  }
}
