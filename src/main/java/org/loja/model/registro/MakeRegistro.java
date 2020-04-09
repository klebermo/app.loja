package org.loja.model.registro;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.UUID;

@Component
public class MakeRegistro extends TextWebSocketHandler {
  @Autowired
  private RegistroService registroServ;

	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
		Registro value = new Gson().fromJson(message.getPayload(), Registro.class);

    String token = UUID.randomUUID().toString().replaceAll("-", "");
    value.setToken(token);
    registroServ.insert(value);
    String result = new Gson().toJson(value);

		session.sendMessage(new TextMessage(result));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
}
