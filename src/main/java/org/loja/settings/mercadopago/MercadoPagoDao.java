package org.loja.settings.mercadopago;

import org.springframework.stereotype.Repository;

@Repository
public class MercadoPagoDao extends org.loja.settings.Dao<MercadoPago> {
  public MercadoPagoDao() {
    super(MercadoPago.class);
  }

  public Boolean processPayment(String status_id) {
    String clientId = this.get().getClientId();
    String clientSecret = this.get().getClientSecret();
    return true;
  }
}
