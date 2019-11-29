package org.loja.model.cliente;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDao extends Dao<Cliente> {
  public ClienteDao() {
    super(Cliente.class);
  }
}
