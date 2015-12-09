package org.cc.automate.core.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketAppConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * Here is an example of configuring a STOMP WebSocket endpoint with SockJS fallback options. 
     * The endpoint is available for clients to connect to at URL path/app/notify:
     * 
     * 
     * ***/
	
    /* 
     * 客户端接收地址
     * (non-Javadoc)
     * @see org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	config.setApplicationDestinationPrefixes("/app");
    	config.enableSimpleBroker("/user/","/topic/");
    	config.setPathMatcher(new AntPathMatcher("_"));//只要是带下划线的
    }

    /* 
     * 服务端接收地址
     * (non-Javadoc)
     * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notify").withSockJS();
    }
	
}
