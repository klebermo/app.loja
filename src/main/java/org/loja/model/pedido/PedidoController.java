package org.loja.model.pedido;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

@Controller
@RequestMapping("pedido")
public class PedidoController extends org.loja.model.Controller<Pedido> {
  @Autowired
  private PedidoService serv;

  public PedidoController() {
    super(Pedido.class);
  }

  @RequestMapping(value = "/add_produto", method=RequestMethod.POST)
  @ResponseBody
  public void add_produto(@RequestParam("pedido") Integer pedido_id, @RequestParam("produto") Integer produto_id) {
    this.serv.add_produto(pedido_id, produto_id);
  }

  @RequestMapping(value = "/remove_produto", method=RequestMethod.POST)
  @ResponseBody
  public void remove_produto(@RequestParam("pedido") Integer pedido_id, @RequestParam("produto") Integer produto_id) {
    this.serv.remove_produto(pedido_id, produto_id);
  }

  @RequestMapping(value = "/produtos", method=RequestMethod.GET)
  @PreAuthorize("hasPermission(#user, 'consulta_produto'")
  public String formProdutos(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("command", this.serv.findBy("id", id));
    return "admin/form/pedido_produtos";
  }
}
