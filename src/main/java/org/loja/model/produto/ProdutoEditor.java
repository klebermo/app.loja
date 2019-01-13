package org.loja.model.produto;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ProdutoEditor extends PropertyEditorSupport {
  @Autowired
  private ProdutoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Produto produto = serv.findBy("id", id);
      setValue(produto);
    } else {
      setValue(null);
    }
  }
}
