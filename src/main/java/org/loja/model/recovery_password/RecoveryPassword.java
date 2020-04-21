package org.loja.model.recovery_password;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;

@Entity
public class RecoveryPassword extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String token;

  @Column(name="data_pedido")
  private Date dataPedido;

	/**
	* Default empty RecoveryPassword constructor
	*/
	public RecoveryPassword() {
		super();
	}

	/**
	* Default RecoveryPassword constructor
	*/
	public RecoveryPassword(Integer id, String token, Date dataPedido) {
		super();
		this.id = id;
		this.token = token;
		this.dataPedido = dataPedido;
	}

	/**
	* Returns value of id
	* @return
	*/
	public Integer getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	* Returns value of token
	* @return
	*/
	public String getToken() {
		return token;
	}

	/**
	* Sets new value of token
	* @param
	*/
	public void setToken(String token) {
		this.token = token;
	}

	/**
	* Returns value of dataPedido
	* @return
	*/
	public Date getDataPedido() {
		return dataPedido;
	}

	/**
	* Sets new value of dataPedido
	* @param
	*/
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	/**
	* Create string representation of RecoveryPassword for printing
	* @return
	*/
	@Override
	public String toString() {
		return "RecoveryPassword [id=" + id + ", token=" + token + ", dataPedido=" + dataPedido + "]";
	}
}
