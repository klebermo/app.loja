package org.loja.settings.mercadopago;

import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService extends org.loja.settings.Service<MercadoPago> {
  public MercadoPagoService() {
    super(MercadoPago.class);
  }
}
