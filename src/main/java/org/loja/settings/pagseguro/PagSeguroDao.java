package org.loja.settings.pagseguro;

import org.springframework.stereotype.Repository;

@Repository
public class PagSeguroDao extends org.loja.settings.Dao<PagSeguro> {
  public PagSeguroDao() {
    super(PagSeguro.class);
  }

  public Boolean processPayment(String payerId, String guid) {
    String clientId = this.get().getClientId();
    String clientSecret = this.get().getClientSecret();
    return true;
  }
}
