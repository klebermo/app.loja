package org.loja.model.registro;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import org.loja.model.maquina.Maquina;

@Entity
public class Registro extends Model{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String token;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Maquina maquina;


	/**
	* Default empty Registro constructor
	*/
	public Registro() {
		super();
	}

	/**
	* Default Registro constructor
	*/
	public Registro(Integer id, String token, Maquina maquina) {
		super();
		this.id = id;
		this.token = token;
		this.maquina = maquina;
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
	* Returns value of maquina
	* @return
	*/
	public Maquina getMaquina() {
		return maquina;
	}

	/**
	* Sets new value of maquina
	* @param
	*/
	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	/**
	* Create string representation of Registro for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Registro [id=" + id + ", token=" + token + ", maquina=" + maquina + "]";
	}
}
