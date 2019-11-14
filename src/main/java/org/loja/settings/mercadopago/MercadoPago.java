package org.loja.settings.mercadopago;

public class MercadoPago extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  private String publicKey;

  private String accessToken;

  public MercadoPago() {
    publicKey = "...";
    accessToken = "...";
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
    return "{publicKey: "+publicKey+", accessToken: "+accessToken+"}";
  }
}
