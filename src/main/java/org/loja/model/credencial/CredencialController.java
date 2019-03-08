package org.loja.model.credencial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("credencial")
public class CredencialController extends org.loja.model.Controller<Credencial> {
  public CredencialController() {
    super(Credencial.class);
  }
}
