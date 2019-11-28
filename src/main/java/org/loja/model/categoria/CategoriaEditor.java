package org.loja.model.categoria;

import java.beans.PropertyEditorSupport;
import java.util.List;
import org.loja.model.titulo.Titulo;

public class CategoriaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      CategoriaService serv = new CategoriaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      List<Categoria> list = serv.select();
      for(Categoria c : list) {
        for(Titulo titulo : c.getNome())
          if(titulo.getConteudo().equals(text))
            setValue(c);
      }
      setValue(null);
    } else {
      setValue(null);
    }
  }
}
