package org.loja.settings.mercadopago;

import org.springframework.stereotype.Repository;

@Repository
public class MercadoPagoDao extends org.loja.settings.Dao<MercadoPago> {
  public MercadoPagoDao() {
    super(MercadoPago.class);
  }
}
