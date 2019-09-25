package org.loja.model.pagina;

<<<<<<< HEAD
import java.beans.PropertyEditorSupport;

public class PaginaEditor extends PropertyEditorSupport {
=======
import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PaginaEditor extends PropertyEditorSupport {
  @Autowired
  private PaginaService serv;

>>>>>>> master
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
<<<<<<< HEAD
      PaginaService serv = new PaginaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
=======
>>>>>>> master
      Pagina pagina = serv.findBy("id", id);
      setValue(pagina);
    } else {
      setValue(null);
    }
  }
}
