package org.loja.model.imagem;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class ImagemDao extends Dao<Imagem> {
  public ImagemDao() {
    super(Imagem.class);
  }
}
