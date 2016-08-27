package com.wesdm.springschedulejob.rescheduler;

import java.text.ParseException;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class Rescheduler {

	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	public void rescheduleCronTrigger(String triggerName, String newCronExpression) {

		// make sure job associated with trigger isn't executing first

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = new TriggerKey(triggerName);
		CronTriggerImpl trigger;
		try {
			trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
			trigger.setCronExpression(newCronExpression);
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();	
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void rescheduleCronJob(String jobName, String newCronExpression) {

		// make sure job associated with trigger isn't executing first

		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(jobName);
		try {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for(Trigger trigger : triggers){
				if(trigger instanceof CronTrigger) {
					((CronTriggerImpl)trigger).setCronExpression(newCronExpression);
					scheduler.rescheduleJob(trigger.getKey(), trigger);
					break;
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
