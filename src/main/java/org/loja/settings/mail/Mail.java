package org.loja.settings.mail;

public class Mail extends org.loja.settings.Model {
  private String host;

  private Integer port;

  private String username;

  private String password;

	/**
	* Default empty Mail constructor
	*/
	public Mail() {
		super();
    this.host = "...";
    this.port = 0;
    this.username = "...";
    this.password = "...";
	}

	/**
	* Default Mail constructor
	*/
	public Mail(String host, Integer port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/**
	* Returns value of host
	* @return
	*/
	public String getHost() {
		return host;
	}

	/**
	* Sets new value of host
	* @param
	*/
	public void setHost(String host) {
		this.host = host;
	}

	/**
	* Returns value of port
	* @return
	*/
	public Integer getPort() {
		return port;
	}

	/**
	* Sets new value of port
	* @param
	*/
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	* Returns value of username
	* @return
	*/
	public String getUsername() {
		return username;
	}

	/**
	* Sets new value of username
	* @param
	*/
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	* Returns value of password
	* @return
	*/
	public String getPassword() {
		return password;
	}

	/**
	* Sets new value of password
	* @param
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* Create string representation of Mail for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Mail [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + "]";
	}
}
