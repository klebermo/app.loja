package org.loja;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteService;
import org.loja.model.usuario.Usuario;
import org.loja.model.usuario.UsuarioService;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.Cookie;

@ControllerAdvice
public class Model {
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private HttpServletResponse response;

  @ModelAttribute("categorias")
  public List<org.loja.model.categoria.Categoria> categorias() {
    List result;
    try {
      org.loja.model.categoria.CategoriaService categoriaServ = new org.loja.model.categoria.CategoriaService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(categoriaServ);
      result = categoriaServ.select();
    } catch (Exception e) {
      e.printStackTrace();
      result = new ArrayList<org.loja.model.categoria.Categoria>();
    }
    return result;
  }

  @ModelAttribute("produtos")
  public List<org.loja.model.produto.Produto> produtos() {
    List result;
    try {
      org.loja.model.produto.ProdutoService produtoServ = new org.loja.model.produto.ProdutoService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(produtoServ);
      result = produtoServ.select();
    } catch (Exception e) {
      e.printStackTrace();
      result = new ArrayList<org.loja.model.produto.Produto>();
    }
    return result;
  }

  @ModelAttribute("paginas")
  public List<org.loja.model.pagina.Pagina> paginas() {
    List result;
    try {
      org.loja.model.pagina.PaginaService paginaServ = new org.loja.model.pagina.PaginaService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(paginaServ);
      result = paginaServ.select();
    } catch (Exception e) {
      e.printStackTrace();
      result = new ArrayList<org.loja.model.pagina.Pagina>();
    }
    return result;
  }

  @ModelAttribute("usuario")
  public Usuario usuario() {
    Usuario result;

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    if(username == null) {
      result = null;
    } else {
      UsuarioService usuarioServ = new UsuarioService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(usuarioServ);
      result = usuarioServ.findBy("username", username);
    }

    return result;
  }

  @ModelAttribute("cliente")
  public Cliente cliente(@CookieValue(value = "cliente", defaultValue = "") String cliente) {
    Cliente result;

    if(cliente.equals("")) {
      Usuario usuario = usuario();
      if(usuario == null) {
        ClienteService clienteServ = new ClienteService();
        AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
        result = new Cliente();
        clienteServ.insert(result);
      } else {
        ClienteService clienteServ = new ClienteService();
        AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
        result = clienteServ.findBy("usuario", usuario);
      }
      Cookie cookie = new Cookie("cliente", result.getId());
      cookie.setMaxAge(-1);
      response.addCookie(cookie);
    } else {
      Usuario usuario = usuario();
      if(usuario == null) {
        ClienteService clienteServ = new ClienteService();
        AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
        result = clienteServ.findBy("id", cliente);
      } else {
        ClienteService clienteServ = new ClienteService();
        AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
        result = clienteServ.findBy("usuario", usuario);

        Cookie cookie1 = new Cookie("cliente", null);
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);

        Cookie cookie2 = new Cookie("cliente", result.getId());
        cookie2.setMaxAge(-1);
        response.addCookie(cookie2);
      }
    }

    return result;
  }

  @ModelAttribute("geral")
  public org.loja.settings.geral.Geral geral() {
    org.loja.settings.geral.Geral result;
    try {
      org.loja.settings.geral.GeralService geralServ = new org.loja.settings.geral.GeralService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(geralServ);
      result = geralServ.get();
    } catch (Exception e) {
      result = new org.loja.settings.geral.Geral();
    }
    return result;
  }

  @ModelAttribute("idiomas_selecionados")
  public List<String> idiomas_selecionados() {
    org.loja.settings.idiomas.Idiomas result;
    try {
      org.loja.settings.idiomas.IdiomasService idiomasServ = new org.loja.settings.idiomas.IdiomasService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(idiomasServ);
      result = idiomasServ.get();
    } catch (Exception e) {
      result = new org.loja.settings.idiomas.Idiomas();
    }
    return result.getListaDeIdiomas();
  }

  @ModelAttribute("lista_de_idiomas")
  public List<String> lista_de_idiomas() throws Exception {
    List<String> lista = new ArrayList<String>();
    org.springframework.core.io.Resource resource = new org.springframework.core.io.ClassPathResource("idiomas.list");
    java.io.InputStream input = resource.getInputStream();
    java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(input, "UTF-8"));
    String line;
    while ((line = br.readLine()) != null)
      lista.add(line);
    return lista;
  }
}
