package org.loja.settings.pagseguro;

public class PagSeguro extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  public String email;

  public String token;

  public PagSeguro() {
    email = "...";
    token = "...";
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
    return "{email: "+email+", token: "+token+"}";
  }
}
