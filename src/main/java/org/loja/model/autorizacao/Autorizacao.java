package org.loja.model.autorizacao;

import org.loja.model.Model;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Autorizacao extends Model implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String nome;

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

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public String getAuthority() {
    return nome;
  }
}
