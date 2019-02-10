package org.loja.settings.paypal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("paypal")
public class PaypalController extends org.loja.settings.Controller<Paypal> {
  public PaypalController() {
    super(Paypal.class);
  }
}
