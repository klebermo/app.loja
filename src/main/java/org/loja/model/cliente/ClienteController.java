package org.loja.model.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cliente")
public class ClienteController extends org.loja.model.Controller<Cliente> {
  @Autowired
  private ClienteService serv;

  public ClienteController() {
    super(Cliente.class);
  }

  @RequestMapping(value = "/cart_size", method=RequestMethod.POST)
  @ResponseBody
  public Integer cart_size(@RequestParam("cliente") Integer cliente_id) {
    return this.serv.cart_size(cliente_id);
  }

  @RequestMapping(value = "/cart_total", method=RequestMethod.POST)
  @ResponseBody
  public Float cart_total(@RequestParam("cliente") Integer cliente_id) {
    return this.serv.cart_total(cliente_id);
  }

  @RequestMapping(value = "/add_to_cart", method=RequestMethod.POST)
  @ResponseBody
  public void add_to_cart(@RequestParam("cliente") Integer cliente_id, @RequestParam("produto") Integer produto_id) {
    this.serv.add_to_cart(cliente_id, produto_id);
  }

  @RequestMapping(value = "/remove_from_cart", method=RequestMethod.POST)
  @ResponseBody
  public void remove_from_cart(@RequestParam("cliente") Integer cliente_id, @RequestParam("produto") Integer produto_id) {
    this.serv.remove_from_cart(cliente_id, produto_id);
  }
}
