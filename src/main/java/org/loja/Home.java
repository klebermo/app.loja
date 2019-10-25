package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.categoria.Categoria;
import org.loja.model.produto.ProdutoService;
import org.loja.model.produto.Produto;
import org.loja.model.pagina.PaginaService;
import org.loja.model.pagina.Pagina;
import org.loja.model.titulo.Titulo;
import org.loja.model.pedido.PedidoService;
import org.loja.model.pedido.Pedido;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class Home {
    @Autowired
    private CategoriaService categoria;

    @Autowired
    private ProdutoService produto;

    @Autowired
    private PaginaService pagina;

    @Autowired
    private PedidoService pedido;

    @Autowired
    private org.loja.model.usuario.UsuarioService usuario;

    @Autowired
    private org.loja.settings.mercadopago.MercadoPagoService mercadoPago;

    @RequestMapping("/")
    public String index(Model model) {
      model.addAttribute("index", "index");
      return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String admin(Model model) {
        return "admin/index";
    }

    @RequestMapping("/c/{nome}")
    public String categoria(Model model, @PathVariable("nome") String nome) {
      List<Categoria> lista = categoria.select();
      for(Categoria c : lista)
        for(Titulo titulo : c.getNome())
          if(titulo.getConteudo().equals(nome))
            model.addAttribute("categoria", c);
      return "index";
    }

    @RequestMapping("/p/{nome}")
    public String produto(Model model, @PathVariable("nome") String nome) {
      List<Produto> lista = produto.select();
      for(Produto p : lista)
        for(Titulo titulo : p.getNome())
          if(titulo.getConteudo().equals(nome))
            model.addAttribute("produto", p);
      return "index";
    }

    @RequestMapping("/forum/{produto}")
    public String produto(Model model, @PathVariable("produto") Integer produto_id) {
      model.addAttribute("forum", produto.findBy("id", produto_id));
      return "index";
    }

    @RequestMapping("/page/{slug}")
    public String pagina(Model model, @PathVariable("slug") String pagina_slug) {
      List<Pagina> lista = pagina.select();
      for(Pagina p : lista)
        for(Titulo titulo : p.getTitulo())
          if(titulo.slug().equals(pagina_slug)) {
            model.addAttribute("pagina", p);
            model.addAttribute("breadcrumb", pagina.breadcrumb(p));
          }
      return "index";
    }

    @RequestMapping("/cart")
    public String cesta(Model model) throws com.mercadopago.exceptions.MPException {
      model.addAttribute("cart", "cart");
      if(usuario().getCesta() != null && !usuario().getCesta().getProdutos().isEmpty())
        model.addAttribute("mercadoPagoPreference", mercadoPago.preference(usuario()));
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
