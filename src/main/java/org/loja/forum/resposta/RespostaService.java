package org.loja.forum.resposta;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class RespostaService extends org.loja.forum.Service<Resposta> {
  public RespostaService() {
    super(Resposta.class);
  }

  public Integer save(Resposta object) {
    this.dao.insert(object);
    return object.getId();
  }
}
