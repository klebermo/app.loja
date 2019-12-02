package org.loja.model.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @RequestMapping(value = "/insert_pedido", method=RequestMethod.POST)
  @ResponseBody
  public String insert_pedido(@RequestParam("cliente") Integer cliente_id, @RequestParam("pedido") Integer pedido_id) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this.serv.insert_pedido(cliente_id, pedido_id));
  }

  @RequestMapping(value = "/update_pedido", method=RequestMethod.POST)
  @ResponseBody
  public String update_pedido(@RequestParam("cliente") Integer cliente_id, @RequestParam("pedido") Integer pedido_id) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this.serv.update_pedido(cliente_id, pedido_id));
  }

  @RequestMapping(value = "/delete_pedido", method=RequestMethod.POST)
  @ResponseBody
  public String delete_pedido(@RequestParam("cliente") Integer cliente_id, @RequestParam("pedido") Integer pedido_id) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this.serv.delete_pedido(cliente_id, pedido_id));
  }

  @RequestMapping(value = "/pedidos", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_pedido'")
  public String formPedidos(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", this.serv.findBy("id", id));
    return "admin/form/cliente_pedidos";
  }
}
