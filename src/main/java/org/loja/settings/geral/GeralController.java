package org.loja.settings.geral;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("geral")
public class GeralController extends org.loja.settings.Controller<Geral> {
  public GeralController() {
    super(Geral.class);
  }
}
