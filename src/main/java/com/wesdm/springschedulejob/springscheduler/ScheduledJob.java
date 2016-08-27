package com.wesdm.springschedulejob.springscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledJob{
	
	@Autowired
	ScheduledTask task;

	@Scheduled(cron="0 0/1 * 1/1 * ?")
	public void execute() {
		System.out.println("ScheduledJob");
		task.doIt();
	}

}
