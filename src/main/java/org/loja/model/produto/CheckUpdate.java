package org.loja.model.produto;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.List;
import org.springframework.web.socket.WebSocketSession;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.CloseStatus;
import org.loja.model.arquivo.Arquivo;
import com.google.gson.Gson;

@Component
public class CheckUpdate extends TextWebSocketHandler {
  List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    ProdutoWrapper value = new Gson().fromJson(message.getPayload(), ProdutoWrapper.class);

    ProdutoService produtoServ = new ProdutoService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(produtoServ);
    Produto current = produtoServ.findBy("nome", value.getProduto().getNome());

    Arquivo [] value_arquivo = (Arquivo []) value.getProduto().getVersaoPaga().toArray();
    Arquivo[] current_arquivo = (Arquivo []) current.getVersaoPaga().toArray();

    if(value_arquivo[0].getVersion() < current_arquivo[0].getVersion()) {
      String result = "true";
      session.sendMessage(new TextMessage(result));
    } else {
      String result = "false";
      session.sendMessage(new TextMessage(result));
    }
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
