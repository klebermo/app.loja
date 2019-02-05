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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Produto extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

  @Column
  private String resumo;

  @Column(length=2097152)
  private String descricao;

  @OneToOne(fetch = FetchType.EAGER)
  private Categoria categoria;

  @Column
  private Float preco;

  @OneToOne
  private Imagem icone;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Imagem> thumbnails;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Arquivo> versaoGratuita;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Arquivo> versaoPaga;

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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
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

  public String toString() {
    return nome;
  }
}
