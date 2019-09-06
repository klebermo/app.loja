package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.produto.ProdutoService;
import org.loja.model.pedido.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class Home {
    @Autowired
    private CategoriaService categoria;

    @Autowired
    private ProdutoService produto;

    @Autowired
    private PedidoService pedido;

    @Autowired
    private org.loja.model.usuario.UsuarioService usuario;

    @RequestMapping("/")
    public String index() {
      return "index";
    }

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signup(@Valid org.loja.model.usuario.Usuario novo_usuario, BindingResult result) {
      Boolean cadastrado = usuario.register(novo_usuario);
      if(cadastrado)
        return "redirect:/signin";
      else
        return "redirect:/signup";
    }

    @RequestMapping("/recover")
    public String recover() {
        return "recover";
    }

    @RequestMapping(value="/recover", method=RequestMethod.POST)
    public String recover(String email) {
      Boolean cadastrado = usuario.recover(email);
      if(cadastrado)
        return "redirect:/signin";
      else
        return "redirect:/recover";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/c/{nome}")
    public String categoria(Model model, @PathVariable("nome") String nome) {
      model.addAttribute("categoria", categoria.findBy("nome", nome));
      return "index";
    }

    @RequestMapping("/p/{nome}")
    public String produto(Model model, @PathVariable("nome") String nome) {
      model.addAttribute("produto", produto.findBy("nome", nome));
      return "index";
    }

    @RequestMapping("/cart")
    public String cesta(Model model) {
      model.addAttribute("cart", "cart");
      return "index";
    }

    @RequestMapping("/orders")
    public String pedidos(Model model) {
      model.addAttribute("orders", "orders");
      return "index";
    }

    @RequestMapping("/order/{order}")
    public String pedidos(Model model, @PathVariable("order") Integer order_id) {
      model.addAttribute("order", pedido.findBy("id", order_id));
      return "index";
    }

    @org.springframework.web.bind.annotation.ModelAttribute("usuario")
    public org.loja.model.usuario.Usuario usuario() {
      return usuario.findBy("username", org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
