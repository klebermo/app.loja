package org.loja.model.arquivo;

import org.loja.model.Model;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Arquivo extends Model implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="file_name")
  private String fileName;

  @Column
  private String type;

  @Column
  private Integer version;

	/**
	* Default empty Arquivo constructor
	*/
	public Arquivo() {
		super();
    this.version = 1;
	}

	/**
	* Default Arquivo constructor
	*/
	public Arquivo(Integer id, String fileName, String type, Integer version) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.type = type;
		this.version = version;
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
	* Returns value of version
	* @return
	*/
	public Integer getVersion() {
		return version;
	}

	/**
	* Sets new value of version
	* @param
	*/
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	* Create string representation of Arquivo for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Arquivo [id=" + id + ", fileName=" + fileName + ", type=" + type + ", version=" + version + "]";
	}
}
