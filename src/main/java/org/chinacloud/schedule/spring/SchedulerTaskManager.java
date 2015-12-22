package org.chinacloud.schedule.spring;

import java.util.List;

import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public class SchedulerTaskManager {
	private ScheduledTaskRegistrar scheduledTaskRegistrar;
	
	public void t(){
		List<CronTask> cronTasks = scheduledTaskRegistrar.getCronTaskList();
	}
	
	
}
