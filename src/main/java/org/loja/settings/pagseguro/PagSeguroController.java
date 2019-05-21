package org.loja.settings.pagseguro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pagseguro")
public class PagSeguroController extends org.loja.settings.Controller<PagSeguro> {
  public PagSeguroController() {
    super(PagSeguro.class);
  }
}
