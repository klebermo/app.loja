package org.loja.model.usuario;

import org.loja.model.Model;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario extends Model implements UserDetails {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String email;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private List<org.loja.model.credencial.Credencial> credenciais;

  @Column
  private Date dataExpiracao;

  @Column
  private Boolean enabled;

  @Column
  private Boolean locked;

  @OneToOne(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  private org.loja.model.cesta.Cesta cesta;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
  @Fetch(FetchMode.SELECT)
  private List<org.loja.model.pedido.Pedido> pedidos;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
    this.password = bcrypt.encode(password);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<org.loja.model.credencial.Credencial> getCredenciais() {
    return credenciais;
  }

  public void setCredenciais(List<org.loja.model.credencial.Credencial> credenciais) {
    this.credenciais = credenciais;
  }

  public Date getDataExpiracao() {
    return dataExpiracao;
  }

  public void setDataExpiracao(Date dataExpiracao) {
    this.dataExpiracao = dataExpiracao;
  }

  public Boolean getEnabled() {
    if(this.enabled == null)
      return true;
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    if(enabled == null)
      this.enabled = false;
    this.enabled = enabled;
  }

  public Boolean getLocked() {
    if(this.locked == null)
      return false;
    return locked;
  }

  public void setLocked(Boolean locked) {
    if(locked == null)
      this.locked = false;
    this.locked = locked;
  }

  public org.loja.model.cesta.Cesta getCesta() {
    return cesta;
  }

  public void setCesta(org.loja.model.cesta.Cesta cesta) {
    this.cesta = cesta;
  }

  public List<org.loja.model.pedido.Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<org.loja.model.pedido.Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }

  @Override
  public boolean isEnabled() {
    return getEnabled();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !this.getLocked();
  }

  @Override
  public boolean isAccountNonExpired() {
    Date hoje = new Date();
    if(this.dataExpiracao == null)
      return true;
    else
      return hoje.before(dataExpiracao);
  }

  @Override
  public java.util.Collection<? extends GrantedAuthority>	getAuthorities() {
    java.util.List<GrantedAuthority> authorities = new java.util.ArrayList<GrantedAuthority>();
    for(org.loja.model.credencial.Credencial c : credenciais)
      authorities.addAll(c.getAutorizacoes());
    return authorities;
  }
}
