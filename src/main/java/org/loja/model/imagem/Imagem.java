package org.loja.model.imagem;

import org.loja.model.Model;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Imagem extends Model implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="file_name")
  private String fileName;

  @Column
  private String type;

	/**
	* Default empty Imagem constructor
	*/
	public Imagem() {
		super();
	}

	/**
	* Default Imagem constructor
	*/
	public Imagem(Integer id, String fileName, String type) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.type = type;
	}

	/**
	* Returns value of serialVersionUID
	* @return
	*/
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	* Returns value of fileName
	* @return
	*/
	public String getFileName() {
		return fileName;
	}

	/**
	* Sets new value of fileName
	* @param
	*/
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	* Returns value of type
	* @return
	*/
	public String getType() {
		return type;
	}

	/**
	* Sets new value of type
	* @param
	*/
	public void setType(String type) {
		this.type = type;
	}

	/**
	* Create string representation of Imagem for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Imagem [id=" + id + ", fileName=" + fileName + ", type=" + type + "]";
	}
}
