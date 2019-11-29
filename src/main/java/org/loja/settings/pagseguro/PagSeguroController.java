package org.loja.settings.pagseguro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.mail.MessagingException;

@Controller
@RequestMapping("pagseguro")
public class PagSeguroController extends org.loja.settings.Controller<PagSeguro> {
  @Autowired
  private PagSeguroService serv;

  public PagSeguroController() {
    super(PagSeguro.class);
  }

  @RequestMapping(value = "/checkout", method=RequestMethod.POST)
  public String checkout(@RequestParam("cliente_id") Integer cliente_id) {
    return "redirect:"+this.serv.checkout(cliente_id);
  }

  @RequestMapping(value = "/process_order", method=RequestMethod.GET)
  public String process_order(@RequestParam("transaction_id") String transaction_id) throws MessagingException {
    return "redirect:"+this.serv.create_order(transaction_id);
  }
}
