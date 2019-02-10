package org.loja.settings.paypal;

import org.springframework.stereotype.Service;

@Service
public class PaypalService extends org.loja.settings.Service<Paypal> {
  public PaypalService() {
    super(Paypal.class);
  }
}
