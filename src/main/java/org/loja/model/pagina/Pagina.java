package org.loja.model.pagina;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.text.Normalizer;
import org.loja.model.titulo.Titulo;
import org.loja.model.texto.Texto;

@Entity
public class Pagina extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Titulo> titulo;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Texto> descricao;

  @OneToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private Pagina parent;

  @Column(length=32)
  private String slug;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Titulo> getTitulo() {
    return titulo;
  }

  public void setTitulo(List<Titulo> titulo) {
    this.titulo = titulo;
  }

  public List<Texto> getDescricao() {
    return descricao;
  }

  public void setDescricao(List<Texto> descricao) {
    this.descricao = descricao;
  }

  public Pagina getParent() {
    return parent;
  }

  public void setParent(Pagina parent) {
    this.parent = parent;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String toString() {
    return titulo.toString();
  }
}
