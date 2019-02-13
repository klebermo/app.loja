package org.loja.model.credencial;

import org.loja.model.Model;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
//import org.loja.model.usuario.UsuarioCredencial;
import org.loja.model.autorizacao.Autorizacao;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credencial extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<Autorizacao> autorizacoes;

  /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
  @Fetch(FetchMode.SELECT)
  @JsonIgnore
  private Set<UsuarioCredencial> usuario;*/

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

  public List<Autorizacao> getAutorizacoes() {
    return autorizacoes;
  }

  public void setAutorizacoes(List<Autorizacao> autorizacoes) {
    this.autorizacoes = autorizacoes;
  }

  /*public Set<UsuarioCredencial> getUsuario() {
    return usuario;
  }

  public void setUsuario(Set<UsuarioCredencial> usuario) {
    this.usuario = usuario;
  }*/

  @Override
  public String toString() {
    return nome;
  }
}
