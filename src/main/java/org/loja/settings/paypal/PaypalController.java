package org.loja.settings.paypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("paypal")
public class PaypalController extends org.loja.settings.Controller<Paypal> {
  @Autowired
  private PaypalService serv;

  public PaypalController() {
    super(Paypal.class);
  }

  @RequestMapping(value = "/checkout", method=RequestMethod.GET)
  public String checkout(@RequestParam("cliente_id") Integer cliente_id, @RequestParam(value="PayerID", required=false) String payerId, @RequestParam(value="guid", required=false) String guid) throws PayPalRESTException {
    return "redirect:"+this.serv.checkout(cliente_id, payerId, guid);
  }
}
