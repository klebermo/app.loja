package org.loja.model.produto;

import org.loja.model.Model;
import org.loja.model.categoria.Categoria;
import java.util.List;
import org.loja.model.imagem.Imagem;
import org.loja.model.arquivo.Arquivo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Titulo> nome;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Resumo> resumo;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Texto> descricao;

  @OneToOne(fetch = FetchType.EAGER)
  private Categoria categoria;

  @Column
  private Float preco;

  @OneToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private Imagem icone;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Imagem> thumbnails;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Arquivo> versaoGratuita;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @Cascade(CascadeType.ALL)
  private List<Arquivo> versaoPaga;

  @OneToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private Forum forum;

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
    return nome.toString();
  }
}
