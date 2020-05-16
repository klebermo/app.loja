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

  private String browser;

  private String browserVersion;

  private String system;

  private String systemVersion;

  private String platform;

  /**
	* Default empty UserAgent constructor
	*/
	public UserAgent(HttpServletRequest req) {
    String user_agent = req.getHeader("user-agent");

    this.id = UUID.randomUUID().toString().replaceAll("-", "");
    this.browser = "";
    this.browserVersion = "";
    this.system = "";
    this.systemVersion = "";
    this.platform = "";
	}

	/**
	* Default empty UserAgent constructor
	*/
	public UserAgent() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	* Default UserAgent constructor
	*/
	public UserAgent(String id, String browser, String browserVersion, String system, String systemVersion, String platform) {
		this.id = id;
		this.browser = browser;
		this.browserVersion = browserVersion;
		this.system = system;
		this.systemVersion = systemVersion;
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
	* Returns value of browser
	* @return
	*/
	public String getBrowser() {
		return browser;
	}

	/**
	* Sets new value of browser
	* @param
	*/
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	* Returns value of browserVersion
	* @return
	*/
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	* Sets new value of browserVersion
	* @param
	*/
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	/**
	* Returns value of system
	* @return
	*/
	public String getSystem() {
		return system;
	}

	/**
	* Sets new value of system
	* @param
	*/
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	* Returns value of systemVersion
	* @return
	*/
	public String getSystemVersion() {
		return systemVersion;
	}

	/**
	* Sets new value of systemVersion
	* @param
	*/
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
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
		return "UserAgent [id=" + id + ", browser=" + browser + ", browserVersion=" + browserVersion + ", system=" + system + ", systemVersion=" + systemVersion + ", codeName=" + codeName + ", platform=" + platform + "]";
	}
}
