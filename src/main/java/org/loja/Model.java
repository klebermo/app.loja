package org.loja;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.loja.model.categoria.Categoria;
import org.loja.model.produto.Produto;
import org.loja.model.pagina.Pagina;
import org.loja.model.pedido.Pedido;
import org.loja.model.cliente.Cliente;
import org.loja.model.usuario.Usuario;
import org.loja.model.credencial.Credencial;
import org.loja.model.autorizacao.Autorizacao;
import org.loja.settings.geral.Geral;
import org.loja.settings.idiomas.Idiomas;
import org.loja.settings.paypal.Paypal;
import org.loja.settings.mercadopago.MercadoPago;
import org.loja.settings.pagseguro.PagSeguro;
import org.loja.model.categoria.CategoriaService;
import org.loja.model.produto.ProdutoService;
import org.loja.model.pagina.PaginaService;
import org.loja.model.pedido.PedidoService;
import org.loja.model.cliente.ClienteService;
import org.loja.model.usuario.UsuarioService;
import org.loja.model.credencial.CredencialService;
import org.loja.model.autorizacao.AutorizacaoService;
import org.loja.settings.geral.GeralService;
import org.loja.settings.idiomas.IdiomasService;
import org.loja.settings.paypal.PaypalService;
import org.loja.settings.mercadopago.MercadoPagoService;
import org.loja.settings.pagseguro.PagSeguroService;
import org.springframework.security.core.context.SecurityContextHolder;
import com.mercadopago.resources.Preference;
import com.mercadopago.exceptions.MPException;
import org.loja.model.topic.TopicService;
import org.loja.model.topic.Topic;

@ControllerAdvice
public class Model {
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
  private CredencialService credencial;

  @Autowired
  private AutorizacaoService autorizacao;

  @Autowired
  private GeralService geral;

  @Autowired
  private IdiomasService idiomas;

  @Autowired
  private PaypalService paypal;

  @Autowired
  private MercadoPagoService mercadoPago;

  @Autowired
  private TopicService topico;

  @Autowired
  private PagSeguroService pagSeguro;

  @ModelAttribute("categorias")
  public List<Categoria> categorias() {
    return categoria.select();
  }

  @ModelAttribute("produtos")
  public List<Produto> produtos() {
    return produto.select();
  }

  @ModelAttribute("paginas")
  public List<Pagina> paginas() {
    return pagina.select();
  }

  @ModelAttribute("pedidos")
  public List<Pedido> pedidos() {
    return pedido.select();
  }

  @ModelAttribute("cliente")
  public Cliente cliente() {
    return cliente.findBy("usuario", usuario());
  }

  @ModelAttribute("usuario")
  public Usuario usuario() {
    return usuario.findBy("username", SecurityContextHolder.getContext().getAuthentication().getName());
  }

  @ModelAttribute("clientes")
  public List<Cliente> clientes() {
    return cliente.select();
  }

  @ModelAttribute("usuarios")
  public List<Usuario> usuarios() {
    return usuario.select();
  }

  @ModelAttribute("credenciais")
  public List<Credencial> credenciais() {
    return credencial.select();
  }

  @ModelAttribute("autorizacoes")
  public List<Autorizacao> autorizacoes() {
    return autorizacao.select();
  }

  @ModelAttribute("geral")
  public Geral geral() {
    return geral.get();
  }

  @ModelAttribute("idiomas")
  public Idiomas idiomas() {
    return idiomas.get();
  }

  @ModelAttribute("paypal")
  public Paypal paypal() {
    return paypal.get();
  }

  @ModelAttribute("mercadoPago")
  public MercadoPago mercadoPago() {
    return mercadoPago.get();
  }

  @ModelAttribute("mercadoPagoPreference")
  public Preference mercadoPagoPreference() throws MPException {
    Cliente u = cliente();
    if(u!= null && u.getCesta() != null && !u.getCesta().getProdutos().isEmpty())
      return mercadoPago.preference(u);
    else
      return null;
  }

  @ModelAttribute("pagSeguro")
  public PagSeguro pagSeguro() {
    return pagSeguro.get();
  }

  @ModelAttribute("lista_de_idiomas")
  public List<String> lista_de_idiomas() throws IOException, UnsupportedEncodingException {
    List<String> lista = new ArrayList<String>();
    Resource resource = new ClassPathResource("idiomas.list");
    InputStream input = resource.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
    String line;
    while ((line = br.readLine()) != null)
      lista.add(line);
    return lista;
  }

  @ModelAttribute("unread")
  public List<Topic> unread() {
    List<Topic> result = new ArrayList<Topic>();

    List<Topic> lista = topico.select();
    for(Topic t : lista)
      if(t.getResposta() == null)
        result.add(t);

    return result;
  }
}
