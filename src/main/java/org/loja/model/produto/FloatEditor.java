package org.loja.model.produto;

import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Component;

@Component
public class FloatEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Float number = Float.parseFloat(text);
      setValue(number);
    } else {
      setValue(null);
    }
  }
}
