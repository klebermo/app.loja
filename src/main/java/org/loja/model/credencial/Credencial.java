package org.loja.model.credencial;

import org.loja.model.Model;
import java.util.Set;
import java.util.Date;
import org.loja.model.autorizacao.Autorizacao;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
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
  private Set<Autorizacao> autorizacoes;

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

  public Set<Autorizacao> getAutorizacoes() {
    return autorizacoes;
  }

  public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
    this.autorizacoes = autorizacoes;
  }

  @Override
  public String toString() {
    return nome;
  }
}
