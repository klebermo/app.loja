package org.loja.model.registro;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteService;
import org.loja.model.usuario.Usuario;
import org.loja.model.usuario.UsuarioService;
import org.loja.model.produto.Produto;
import org.loja.model.produto.ProdutoService;

@Component
public class CheckRegistro extends TextWebSocketHandler {
  @Autowired
  private RegistroService registroServ;

  @Autowired
  private UsuarioService usuarioServ;

  @Autowired
  private ProdutoService produtoServ;

  @Autowired
  private ClienteService clienteServ;

	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
    Registro value = new Gson().fromJson(message.getPayload(), Registro.class);

    String email_usuario = value.getUsuario().getEmail();
    Usuario usuario = usuarioServ.findBy("email", email_usuario);

    String nome_produto = value.getProduto().getNome();
    Produto produto = produtoServ.findBy("nome", nome_produto);

    Cliente cliente = clienteServ.findBy("usuario", usuario);
    if(cliente.produtosComprados().contains(produto))
      value = registroServ.findBy("produto", produto);

    String result = new Gson().toJson(value);
		session.sendMessage(new TextMessage(result));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

  @Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		sessions.remove(session);
	}
}
