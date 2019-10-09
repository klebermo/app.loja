package org.loja.settings.mercadopago;

public class MercadoPago extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  private String clientId;

  private String clientSecret;

  private String publicKey;

  private String accessToken;

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

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String toString() {
    return "{clientId: "+clientId+", clientSecret: "+clientSecret+", accessToken: "+accessToken+"}";
  }
}
