package org.loja.settings.paypal;

public class Paypal extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  private String clientId;

  private String clientSecret;

  public Paypal() {
    clientId = "...";
    clientSecret = "...";
  }

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

  public String toString() {
    return "{clientId: "+clientId+", clientSecret: "+clientSecret+"}";
  }
}
