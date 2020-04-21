package org.loja.model.maquina;

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

  @Column(name="host_name")
  private String hostName;

  @Column(name="unique_id")
  private String uniqueId;

  @Column
  private String ram;

  @Column(name="product_name")
  private String productName;

  @Column(name="kernel_type")
  private String kernelType;

  @Column(name="kernel_version")
  private String kernelVersion;

  @Column(name="cpu_arch")
  private String cpuArch;

	/**
	* Default empty Maquina constructor
	*/
	public Maquina() {
		super();
	}

	/**
	* Default Maquina constructor
	*/
	public Maquina(Integer id, String hostName, String uniqueId, String ram, String productName, String kernelType, String kernelVersion, String cpuArch) {
		super();
		this.id = id;
		this.hostName = hostName;
		this.uniqueId = uniqueId;
		this.ram = ram;
		this.productName = productName;
		this.kernelType = kernelType;
		this.kernelVersion = kernelVersion;
		this.cpuArch = cpuArch;
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
	* Returns value of hostName
	* @return
	*/
	public String getHostName() {
		return hostName;
	}

	/**
	* Sets new value of hostName
	* @param
	*/
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	* Returns value of uniqueId
	* @return
	*/
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	* Sets new value of uniqueId
	* @param
	*/
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	* Returns value of ram
	* @return
	*/
	public String getRam() {
		return ram;
	}

	/**
	* Sets new value of ram
	* @param
	*/
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	* Returns value of productName
	* @return
	*/
	public String getProductName() {
		return productName;
	}

	/**
	* Sets new value of productName
	* @param
	*/
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	* Returns value of kernelType
	* @return
	*/
	public String getKernelType() {
		return kernelType;
	}

	/**
	* Sets new value of kernelType
	* @param
	*/
	public void setKernelType(String kernelType) {
		this.kernelType = kernelType;
	}

	/**
	* Returns value of kernelVersion
	* @return
	*/
	public String getKernelVersion() {
		return kernelVersion;
	}

	/**
	* Sets new value of kernelVersion
	* @param
	*/
	public void setKernelVersion(String kernelVersion) {
		this.kernelVersion = kernelVersion;
	}

	/**
	* Returns value of cpuArch
	* @return
	*/
	public String getCpuArch() {
		return cpuArch;
	}

	/**
	* Sets new value of cpuArch
	* @param
	*/
	public void setCpuArch(String cpuArch) {
		this.cpuArch = cpuArch;
	}

	/**
	* Create string representation of Maquina for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Maquina [id=" + id + ", hostName=" + hostName + ", uniqueId=" + uniqueId + ", ram=" + ram + ", productName=" + productName + ", kernelType=" + kernelType + ", kernelVersion=" + kernelVersion + ", cpuArch=" + cpuArch + "]";
	}
}
