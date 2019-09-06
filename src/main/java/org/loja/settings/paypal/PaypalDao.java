package org.loja.settings.paypal;

import org.springframework.stereotype.Repository;

@Repository
public class PaypalDao extends org.loja.settings.Dao<Paypal> {
  public PaypalDao() {
    super(Paypal.class);
  }

  public Boolean processPayment(String payerId, String guid) {
    String clientId = this.get().getClientId();
    String clientSecret = this.get().getClientSecret();
    APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
    return "";
  }
}
