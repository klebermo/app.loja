package org.loja.ativacao.registro;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import org.loja.ativacao.token.Token;
import org.loja.model.produto.Produto;
import org.loja.ativacao.maquina.Maquina;

@Entity
public class Registro extends Model{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Produto produto;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Token token;

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
	public Registro(Integer id, Produto produto, Token token, Maquina maquina) {
		super();
		this.id = id;
		this.produto = produto;
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
	* Returns value of produto
	* @return
	*/
	public Produto getProduto() {
		return produto;
	}

	/**
	* Sets new value of produto
	* @param
	*/
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	* Returns value of token
	* @return
	*/
	public Token getToken() {
		return token;
	}

	/**
	* Sets new value of token
	* @param
	*/
	public void setToken(Token token) {
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
		return "Registro [id=" + id + ", produto=" + produto + ", token=" + token + ", maquina=" + maquina + "]";
	}
}
