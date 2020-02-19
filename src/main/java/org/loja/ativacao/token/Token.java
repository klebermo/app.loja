package org.loja.ativacao.token;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class Token extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String accessToken;

  @Column
  private Date dataAtivacao;

	/**
	* Default empty Token constructor
	*/
	public Token() {
		super();
	}

	/**
	* Default Token constructor
	*/
	public Token(Integer id, String accessToken, Date dataAtivacao) {
		super();
		this.id = id;
		this.accessToken = accessToken;
		this.dataAtivacao = dataAtivacao;
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
	* Returns value of accessToken
	* @return
	*/
	public String getAccessToken() {
		return accessToken;
	}

	/**
	* Sets new value of accessToken
	* @param
	*/
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	* Returns value of dataAtivacao
	* @return
	*/
	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	/**
	* Sets new value of dataAtivacao
	* @param
	*/
	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	/**
	* Create string representation of Token for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Token [id=" + id + ", accessToken=" + accessToken + ", dataAtivacao=" + dataAtivacao + "]";
	}
}
