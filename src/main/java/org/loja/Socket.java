package org.loja;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.loja.model.registro.CheckRegistro;
import org.loja.model.registro.MakeRegistro;

@Configuration
@EnableWebSocket
public class Socket implements WebSocketConfigurer {
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new CheckRegistro(), "/check_registration").setAllowedOrigins("*");
    registry.addHandler(new MakeRegistro(), "/register_product").setAllowedOrigins("*");
	}
}
