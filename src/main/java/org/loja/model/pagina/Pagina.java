package org.loja.model.pagina;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
<<<<<<< HEAD
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.text.Normalizer;
=======
>>>>>>> master

@Entity
public class Pagina extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

<<<<<<< HEAD
  @Column(length=64)
=======
  @Column
>>>>>>> master
  private String titulo;

  @Column(length=2097152)
  private String descricao;

  @OneToOne(fetch = FetchType.EAGER)
<<<<<<< HEAD
  @Fetch(FetchMode.SELECT)
  private Pagina parent;

  @Column(length=64)
  private String slug;

=======
  private Pagina parent;

>>>>>>> master
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

<<<<<<< HEAD
  public void setTitulo(String titulo) {
=======
  public void setTitulo(String nome) {
>>>>>>> master
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

<<<<<<< HEAD
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

=======
>>>>>>> master
  public String toString() {
    return titulo;
  }
}
