package org.loja.settings.pagseguro;

public class PagSeguro extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  private String clientId;

  private String clientSecret;

  public String email;

  public String token;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String toString() {
    return "{clientId: "+clientId+", clientSecret: "+clientSecret+"}";
  }
}
