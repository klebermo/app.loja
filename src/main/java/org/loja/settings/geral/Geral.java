package org.loja.settings.geral;

import org.loja.model.imagem.Imagem;

public class Geral extends org.loja.settings.Model {
  private static final long serialVersionUID = 1L;

  private String titulo;

  private String desenvolvedor;

  private String email;

  private Imagem logo;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDesenvolvedor() {
    return desenvolvedor;
  }

  public void setDesenvolvedor(String desenvolvedor) {
    this.desenvolvedor = desenvolvedor;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Imagem getLogo() {
    return logo;
  }

  public void setLogo(Imagem logo) {
    this.logo = logo;
  }

  public String toString() {
    return "{titulo: "+titulo+", desenvolvedor: "+desenvolvedor+", e-mail: "+email+"}";
  }
}
