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
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OrderColumn;
import org.loja.model.categoria.Categoria;
import javax.persistence.Column;
import org.loja.model.imagem.Imagem;
import org.loja.model.arquivo.Arquivo;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import org.loja.forum.forum.Forum;

@Entity
public class Produto extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

  @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
  @OrderColumn
  private List<Resumo> resumo;

  @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
  @OrderColumn
  private List<Texto> descricao;

  @OneToOne(fetch = FetchType.EAGER)
  private Categoria categoria;

  @Column
  private Float preco;

  @OneToOne(fetch = FetchType.EAGER)
  private Imagem icone;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Imagem> thumbnails;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Arquivo> versaoGratuita;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Arquivo> versaoPaga;

  @OneToOne(fetch = FetchType.EAGER)
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

  public Set<Imagem> getThumbnails() {
    return thumbnails;
  }

  public void setThumbnails(Set<Imagem> thumbnails) {
    this.thumbnails = thumbnails;
  }

  public Set<Arquivo> getVersaoGratuita() {
    return versaoGratuita;
  }

  public void setVersaoGratuita(Set<Arquivo> versaoGratuita) {
    this.versaoGratuita = versaoGratuita;
  }

  public Set<Arquivo> getVersaoPaga() {
    return versaoPaga;
  }

  public void setVersaoPaga(Set<Arquivo> versaoPaga) {
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
