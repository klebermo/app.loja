package org.loja.ativacao.maquina;

import javax.persistence.Entity;
import org.loja.model.Model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class Maquina extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String cpu;

  @Column
  private String placaMae;

  @Column
  private Integer ram;

  @Column
  private String placaVideo;

  @Column
  private Integer armazenamento;

	/**
	* Default empty Maquina constructor
	*/
	public Maquina() {
		super();
	}

	/**
	* Default Maquina constructor
	*/
	public Maquina(Integer id, String cpu, String placaMae, Integer ram, String placaVideo, Integer armazenamento) {
		super();
		this.id = id;
		this.cpu = cpu;
		this.placaMae = placaMae;
		this.ram = ram;
		this.placaVideo = placaVideo;
		this.armazenamento = armazenamento;
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
	* Returns value of cpu
	* @return
	*/
	public String getCpu() {
		return cpu;
	}

	/**
	* Sets new value of cpu
	* @param
	*/
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	/**
	* Returns value of placaMae
	* @return
	*/
	public String getPlacaMae() {
		return placaMae;
	}

	/**
	* Sets new value of placaMae
	* @param
	*/
	public void setPlacaMae(String placaMae) {
		this.placaMae = placaMae;
	}

	/**
	* Returns value of ram
	* @return
	*/
	public Integer getRam() {
		return ram;
	}

	/**
	* Sets new value of ram
	* @param
	*/
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	/**
	* Returns value of placaVideo
	* @return
	*/
	public String getPlacaVideo() {
		return placaVideo;
	}

	/**
	* Sets new value of placaVideo
	* @param
	*/
	public void setPlacaVideo(String placaVideo) {
		this.placaVideo = placaVideo;
	}

	/**
	* Returns value of armazenamento
	* @return
	*/
	public Integer getArmazenamento() {
		return armazenamento;
	}

	/**
	* Sets new value of armazenamento
	* @param
	*/
	public void setArmazenamento(Integer armazenamento) {
		this.armazenamento = armazenamento;
	}

	/**
	* Create string representation of Maquina for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Maquina [id=" + id + ", cpu=" + cpu + ", placaMae=" + placaMae + ", ram=" + ram + ", placaVideo=" + placaVideo + ", armazenamento=" + armazenamento + "]";
	}
}
