package org.cc.automate.core.ws;

import java.io.IOException;
import java.util.Vector;

import org.cc.automate.core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class WebsocketMessageHandler extends TextWebSocketHandler{
protected Logger logger = LoggerFactory.getLogger(WebsocketMessageHandler.class);
	//同步类型的集合
	public static Vector<WebSocketSession> sessions = new Vector<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("connection builded...");
		sessions.add(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug("Received: " + message);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
		logger.error("connection failed {}", exception);
		sessions.remove(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		logger.debug("connection is closed {}", status);
		sessions.remove(session);
	}
	
	/**
	 * 发送消息给所有用户
	 * @param messageText
	 * @throws IOException
	 */
	public void sendMessageToUsers(String messageText){
		for(WebSocketSession session : sessions){
			if(session.isOpen()){
				try {
					session.sendMessage(new TextMessage(messageText));
				} catch (IOException e) {
					logger.error("connection failed {}", e);
				}
			}
		}
	}
	
	/**
	 * 发送消息给指定用户
	 * @param userId
	 * @param messageText
	 */
	public boolean sendMessageToUser(String userId, String messageText){
		boolean flag = false;
		for(WebSocketSession session : sessions){
			String USER_ID = (String)session.getAttributes().get(Constant.USER_ID);
			if(session.isOpen() && USER_ID != null && userId.equals(USER_ID)){
				try {
					session.sendMessage(new TextMessage(messageText));
					flag = true;
				} catch (IOException e) {
					logger.error("connection failed {}", e);
				}
			}
		}
		
		return flag;
	}
}
