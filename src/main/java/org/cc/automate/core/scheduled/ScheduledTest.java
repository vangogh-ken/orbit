package org.cc.automate.core.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTest {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	@Scheduled(cron="* * * * * ?")
	public void exe(){
		//System.out.println("task");
		//messagingTemplate.convertAndSendToUser("admin", "/info", "greeting");
	}
}
