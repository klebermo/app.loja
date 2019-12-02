package org.loja.model.pedido;

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
@RequestMapping("pedido")
public class PedidoController extends org.loja.model.Controller<Pedido> {
  @Autowired
  private PedidoService serv;

  public PedidoController() {
    super(Pedido.class);
  }

  @RequestMapping(value = "/insert_produto", method=RequestMethod.POST)
  @ResponseBody
  public String insert_produto(@RequestParam("pedido") Integer pedido_id, @RequestParam("produto") Integer produto_id) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this.serv.insert_produto(pedido_id, produto_id));
  }

  @RequestMapping(value = "/delete_produto", method=RequestMethod.POST)
  @ResponseBody
  public String delete_produto(@RequestParam("pedido") Integer pedido_id, @RequestParam("produto") Integer produto_id) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this.serv.delete_produto(pedido_id, produto_id));
  }

  @RequestMapping(value = "/produtos", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_produto'")
  public String formProdutos(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", this.serv.findBy("id", id));
    return "admin/form/pedido_produtos";
  }
}
