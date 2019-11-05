package org.loja.model.produto;

import java.beans.PropertyEditorSupport;

public class ProdutoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ProdutoService serv = new ProdutoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Produto produto = serv.findBy("id", id);
      setValue(produto);
    } else {
      setValue(null);
    }
  }
}
