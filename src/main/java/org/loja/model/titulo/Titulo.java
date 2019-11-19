package org.loja.model.titulo;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.text.Normalizer;

@Entity
public class Titulo extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String idioma;

  @Column(length=32)
  private String conteudo;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public String slug() {
    return Normalizer.normalize(conteudo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "_");
  }

  public String toString() {
    return conteudo;
  }
}
