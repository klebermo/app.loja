package org.loja.settings.email;

public class Email extends org.loja.settings.Model {
  private static final long serialVersionUID = 1L;

  private String protocol;

  private String host;

  private Integer port;

  private String username;

  private String password;

  public Email() {
    this.protocol = "";
    this.host = "";
    this.port = 0;
    this.username = "";
    this.password = "";
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "{protocol: "+protocol+", host: "+host+", port: "+port+"}";
  }
}
