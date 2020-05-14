package org.loja.model.user_agent;

import javax.persistence.Entity;
import org.loja.model.Model;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

@Entity
public class UserAgent extends Model {
  @Id
  private String id;

  @Column
  private String codeName;

  @Column
  private String name;

  @Column
  private String version;

  @Column
  private String cookiesEnabled;

  @Column
  private String language;

  @Column
  private String platform;


	/**
	* Default empty UserAgent constructor
	*/
	public UserAgent() {
    this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

  /**
	* Default empty UserAgent constructor
	*/
	public UserAgent(HttpServletRequest req) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
    String user_agent = req.getHeader("User-Agent");
    this.codeName = "";
    this.name = "";
    this.version = "";
    this.cookiesEnabled = "";
    this.language = "";
    this.platform = "";
	}

  /**
	* Default UserAgent constructor
	*/
	public UserAgent(String codeName, String name, String version, String cookiesEnabled, String language, String platform) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.codeName = codeName;
		this.name = name;
		this.version = version;
		this.cookiesEnabled = cookiesEnabled;
		this.language = language;
		this.platform = platform;
	}

	/**
	* Default UserAgent constructor
	*/
	public UserAgent(String id, String codeName, String name, String version, String cookiesEnabled, String language, String platform) {
		this.id = id;
		this.codeName = codeName;
		this.name = name;
		this.version = version;
		this.cookiesEnabled = cookiesEnabled;
		this.language = language;
		this.platform = platform;
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
	* Returns value of codeName
	* @return
	*/
	public String getCodeName() {
		return codeName;
	}

	/**
	* Sets new value of codeName
	* @param
	*/
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets new value of name
	* @param
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Returns value of version
	* @return
	*/
	public String getVersion() {
		return version;
	}

	/**
	* Sets new value of version
	* @param
	*/
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	* Returns value of cookiesEnabled
	* @return
	*/
	public String getCookiesEnabled() {
		return cookiesEnabled;
	}

	/**
	* Sets new value of cookiesEnabled
	* @param
	*/
	public void setCookiesEnabled(String cookiesEnabled) {
		this.cookiesEnabled = cookiesEnabled;
	}

	/**
	* Returns value of language
	* @return
	*/
	public String getLanguage() {
		return language;
	}

	/**
	* Sets new value of language
	* @param
	*/
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	* Returns value of platform
	* @return
	*/
	public String getPlatform() {
		return platform;
	}

	/**
	* Sets new value of platform
	* @param
	*/
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	* Create string representation of UserAgent for printing
	* @return
	*/
	@Override
	public String toString() {
		return "UserAgent [id=" + id + ", codeName=" + codeName + ", name=" + name + ", version=" + version + ", cookiesEnabled=" + cookiesEnabled + ", language=" + language + ", platform=" + platform + "]";
	}
}
