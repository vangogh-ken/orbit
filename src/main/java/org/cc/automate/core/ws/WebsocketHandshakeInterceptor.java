package org.cc.automate.core.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.cc.automate.core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class WebsocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor{  
	private static Logger logger = LoggerFactory.getLogger(WebsocketHandshakeInterceptor.class);
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
        //System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
            	//TODO 
                attributes.put(Constant.USER_ID, session.getAttribute(Constant.USER_ID));
            }
        }
        //super.beforeHandshake(request, response, wsHandler, attributes);
        return super.beforeHandshake(request, response, wsHandler, attributes);//经过默认的拦截器
        //return true;
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        super.afterHandshake(request, response, wsHandler, ex);
    }  
  
}  
