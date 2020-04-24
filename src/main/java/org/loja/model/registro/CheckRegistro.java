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
import org.loja.model.produto.Produto;
import org.loja.model.produto.ProdutoService;
import org.loja.model.maquina.Maquina;
import org.loja.model.maquina.MaquinaService;
import org.loja.model.pedido.Pedido;

@Component
public class CheckRegistro extends TextWebSocketHandler {
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
    RegistroWrapper value = new Gson().fromJson(message.getPayload(), RegistroWrapper.class);

		Cliente cliente_data = value.getCliente();
		ClienteService clienteServ = new ClienteService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
		Cliente cliente = clienteServ.findBy("id", cliente_data.getId());

		Produto produto_data = value.getProduto();
		ProdutoService produtoServ = new ProdutoService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(produtoServ);
		Produto produto = produtoServ.findBy("nome", produto_data.getNome());

		Maquina maquina_data = value.getMaquina();
		MaquinaService maquinaServ = new MaquinaService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(maquinaServ);
		Maquina maquina = maquinaServ.findBy("id", maquina_data.getId());

		Boolean foiComprado = false;
		Pedido target = new Pedido();
		for(Pedido pedido : cliente.getPedidos()) {
			if(pedido.getProdutos().contains(produto)) {
				foiComprado = true;
				target = pedido;
				break;
			}
		}

		if(foiComprado) {
			String result = new Gson().toJson(target.getRegistro());
			session.sendMessage(new TextMessage(result));
		} else {
			Registro novo = new Registro();
			novo.setId(-1);
			novo.setMaquina(null);
			String result = new Gson().toJson(novo);
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
