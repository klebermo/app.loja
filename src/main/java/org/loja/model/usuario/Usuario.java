package org.loja.model.usuario;

import javax.persistence.Entity;
import org.loja.model.Model;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Set;
import org.loja.model.credencial.Credencial;
import org.loja.model.recovery_password.RecoveryPassword;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Usuario extends Model implements UserDetails {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String username;

  @Column
  private String password;

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="usuario_credenciais", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="credencial_id")})
  private List<Credencial> credenciais;

  @Column(name="data_expiracao")
  private Date dataExpiracao;

  @Column
  private Boolean enabled;

  @Column
  private Boolean locked;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="usuario_recovery", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="recovery_id")})
  private Set<RecoveryPassword> recoveryPassword;

  /**
	* Default empty Usuario constructor
	*/
	public Usuario() {
		super();
	}

	/**
	* Default Usuario constructor
	*/
	public Usuario(Integer id, String username, String password, String firstName, String lastName, String email, List<Credencial> credenciais, Date dataExpiracao, Boolean enabled, Boolean locked, Set<RecoveryPassword> recoveryPassword) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.credenciais = credenciais;
		this.dataExpiracao = dataExpiracao;
		this.enabled = enabled;
		this.locked = locked;
		this.recoveryPassword = recoveryPassword;
	}

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

  public List<Credencial> getCredenciais() {
    return credenciais;
  }

  public void setCredenciais(List<Credencial> credenciais) {
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

  public Set<RecoveryPassword> getRecoveryPassword() {
    return recoveryPassword;
  }

  public void setRecoveryPassword(Set<RecoveryPassword> recoveryPassword) {
    this.recoveryPassword = recoveryPassword;
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
