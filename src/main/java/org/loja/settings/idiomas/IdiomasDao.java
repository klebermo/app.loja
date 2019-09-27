package org.loja.settings.idiomas;

import org.springframework.stereotype.Repository;

@Repository
public class IdiomasDao extends org.loja.settings.Dao<Idiomas> {
  public IdiomasDao() {
    super(Idiomas.class);
  }
}
