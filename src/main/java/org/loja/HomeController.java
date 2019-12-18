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
import org.loja.model.cliente.ClienteService;
import org.loja.model.cliente.Cliente;
import org.loja.model.usuario.Usuario;
import org.loja.model.usuario.UsuarioService;
import org.loja.forum.topic.TopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HomeService home;

    @Autowired
    private CategoriaService categoria;

    @Autowired
    private ProdutoService produto;

    @Autowired
    private PaginaService pagina;

    @Autowired
    private PedidoService pedido;

    @Autowired
    private ClienteService cliente;

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private TopicService topico;

    @RequestMapping("/")
    public String index(Model model) {
      model.addAttribute("index", "index");
      return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
      model.addAttribute("login", "login");
      return "login";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String admin(Model model) {
      model.addAttribute("admin", "admin");
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
      model.addAttribute("produto", produto.findBy("nome", nome));
      return "index";
    }

    @RequestMapping("/page/{titulo}")
    public String pagina(Model model, @PathVariable("titulo") String titulo) {
      Pagina page = new Pagina();
      for(Pagina p : pagina.select())
        for(Titulo t : p.getTitulo())
          if(t.getConteudo().equals(titulo))
            page = p;
      model.addAttribute("pagina", page);
      model.addAttribute("breadcrumb", pagina.breadcrumb(page));
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

    @RequestMapping("/messages")
    public String messages(Model model) {
      model.addAttribute("messages", topico.select());
      return "admin/index";
    }

    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String formRegister(Model model) {
      model.addAttribute("command", new Usuario());
      model.addAttribute("login", "login");
      return "register";
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    @ResponseBody
    public void doRegister(@ModelAttribute("command") Usuario object) throws Exception {
      home.register(object);
    }

    @RequestMapping(value = "/recoverPassword", method=RequestMethod.GET)
    public String formRecover(Model model) {
      model.addAttribute("login", "login");
      return "recover";
    }

    @RequestMapping(value = "/recoverPassword", method=RequestMethod.POST)
    public String doRecoverPassword(@RequestParam("email") String email, @RequestParam(value="token", required=false) String token) throws Exception {
      return "redirect:" + home.recoverPassword(email, token);
    }

    @RequestMapping(value = "/credenciais", method=RequestMethod.GET)
    @PreAuthorize("hasPermission(#user, 'consulta_credencial'")
    public String formCredenciais(Model model, @RequestParam("id") Integer id) {
      model.addAttribute("command", usuario.findBy("id", id));
      return "admin/form/credenciais";
    }

    @RequestMapping(value = "/toggle_credencial", method=RequestMethod.POST)
    @ResponseBody
    public void toggle_credencial(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("credencial_id") Integer credencial_id) {
      home.toggle_credencial(usuario_id, credencial_id);
    }
}
