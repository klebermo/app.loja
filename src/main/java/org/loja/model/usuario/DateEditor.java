package org.loja.model.usuario;

import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

@Component
public class DateEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date data = df.parse(text);
        setValue(data);
      } catch (ParseException e) {
        setValue(null);
      }
    } else {
      setValue(null);
    }
  }
}
