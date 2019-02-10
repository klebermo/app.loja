package org.loja.settings.paypal;

import org.springframework.stereotype.Repository;

@Repository
public class PaypalDao extends org.loja.settings.Dao<Paypal> {
  public PaypalDao() {
    super(Paypal.class);
  }
}
