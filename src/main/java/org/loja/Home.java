package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.produto.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class Home {
    @Autowired
    private CategoriaService categoria;

    @Autowired
    private ProdutoService produto;

    @RequestMapping("/")
    public String index() {
      return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String admin(Model model) {
        model.addAttribute("dashboard", "dashboard");
        model.addAttribute("classe", null);
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
}
