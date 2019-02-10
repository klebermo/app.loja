package org.loja.settings.geral;

import org.springframework.stereotype.Repository;

@Repository
public class GeralDao extends org.loja.settings.Dao<Geral> {
  public GeralDao() {
    super(Geral.class);
  }
}
