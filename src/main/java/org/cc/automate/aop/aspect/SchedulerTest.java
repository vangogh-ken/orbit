package org.cc.automate.aop.aspect;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTest {
	@Scheduled(cron="0/1 * * * * ?")
	public void exe(){
		//System.out.println("executing");
	}
	
	@Scheduled(cron="0/1 * * * * ?")
	public void exe2(){
		//System.out.println("executed");
	}
}
