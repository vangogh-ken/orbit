package org.cc.automate.aop.aspect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTest {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Scheduled(cron="1/12 * * * * ?")
	//@Scheduled(cron="* * * * * ?")
	public void exe(){
		//System.out.println("send message");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "true");
		result.put("type", "MESSGAE");
		result.put("message", "hello");
		messagingTemplate.convertAndSendToUser("admin", "/info", result);
	}
	
	@Scheduled(cron="0/1 * * * * ?")
	public void exe2(){
		//System.out.println("executed");
	}
}
