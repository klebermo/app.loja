package org.loja.model.produto;

import org.loja.model.Model;
import org.loja.model.categoria.Categoria;
import java.util.Set;
import org.loja.model.imagem.Imagem;
import org.loja.model.arquivo.Arquivo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.loja.model.titulo.Titulo;
import org.loja.model.resumo.Resumo;
import org.loja.model.texto.Texto;
import org.loja.forum.forum.Forum;

@Entity
public class Produto extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Titulo> nome;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Resumo> resumo;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Texto> descricao;

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

  public Set<Titulo> getNome() {
    return nome;
  }

  public void setNome(Set<Titulo> nome) {
    this.nome = nome;
  }

  public Set<Resumo> getResumo() {
    return resumo;
  }

  public void setResumo(Set<Resumo> resumo) {
    this.resumo = resumo;
  }

  public Set<Texto> getDescricao() {
    return descricao;
  }

  public void setDescricao(Set<Texto> descricao) {
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
    return nome.toString();
  }
}
