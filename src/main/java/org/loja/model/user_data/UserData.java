package org.loja.model.user_data;

import javax.persistence.Entity;
import org.loja.model.Model;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Entity
public class UserData extends Model {
  @Id
  private String id;

  @Column
  private String ipAddress;

  @Column
  private String country;

  @Column
  private String state;

  @Column
  private String city;

  @Column
  private Date dataAcesso;

  @Column
  private String urlAcesso;

	/**
	* Default empty UserData constructor
	*/
	public UserData() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

  /**
	* Default empty UserAgent constructor
	*/
	public UserData(HttpServletRequest req) {
    this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.ipAddress = req.getRemoteAddr();
		this.country = "";
		this.state = "";
		this.city = "";
		this.dataAcesso = new Date();
		this.urlAcesso = req.getRequestURI();
  }

  /**
	* Default UserData constructor
	*/
	public UserData(String ipAddress, String country, String state, String city, Date dataAcesso, String urlAcesso) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.ipAddress = ipAddress;
		this.country = country;
		this.state = state;
		this.city = city;
		this.dataAcesso = dataAcesso;
		this.urlAcesso = urlAcesso;
	}

	/**
	* Default UserData constructor
	*/
	public UserData(String id, String ipAddress, String country, String state, String city, Date dataAcesso, String urlAcesso) {
		this.id = id;
		this.ipAddress = ipAddress;
		this.country = country;
		this.state = state;
		this.city = city;
		this.dataAcesso = dataAcesso;
		this.urlAcesso = urlAcesso;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of ipAddress
	* @return
	*/
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	* Sets new value of ipAddress
	* @param
	*/
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	* Returns value of country
	* @return
	*/
	public String getCountry() {
		return country;
	}

	/**
	* Sets new value of country
	* @param
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	* Returns value of state
	* @return
	*/
	public String getState() {
		return state;
	}

	/**
	* Sets new value of state
	* @param
	*/
	public void setState(String state) {
		this.state = state;
	}

	/**
	* Returns value of city
	* @return
	*/
	public String getCity() {
		return city;
	}

	/**
	* Sets new value of city
	* @param
	*/
	public void setCity(String city) {
		this.city = city;
	}

	/**
	* Returns value of dataAcesso
	* @return
	*/
	public Date getDataAcesso() {
		return dataAcesso;
	}

	/**
	* Sets new value of dataAcesso
	* @param
	*/
	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	/**
	* Returns value of urlAcesso
	* @return
	*/
	public String getUrlAcesso() {
		return urlAcesso;
	}

	/**
	* Sets new value of urlAcesso
	* @param
	*/
	public void setUrlAcesso(String urlAcesso) {
		this.urlAcesso = urlAcesso;
	}

	/**
	* Create string representation of UserData for printing
	* @return
	*/
	@Override
	public String toString() {
		return "UserData [id=" + id + ", ipAddress=" + ipAddress + ", country=" + country + ", state=" + state + ", city=" + city + ", dataAcesso=" + dataAcesso + ", urlAcesso=" + urlAcesso + "]";
	}
}
