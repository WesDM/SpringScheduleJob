package com.wesdm.springschedulejob;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.wesdm.springschedulejob.springscheduler.ScheduledTask;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private ScheduledTask scheduledTask;
	
	@PostConstruct
	public void scheduleDynamicJobs() throws ClassNotFoundException, NoSuchMethodException, ParseException, SchedulerException{
		MethodInvokingJobDetailFactoryBean dynamicJob = new MethodInvokingJobDetailFactoryBean();
		dynamicJob.setName("dynamicTask");
		dynamicJob.setTargetObject(scheduledTask);
		dynamicJob.setTargetMethod("dynamicTask");
		dynamicJob.setConcurrent(Boolean.FALSE);
		dynamicJob.afterPropertiesSet();
		
		CronTriggerFactoryBean dynamicJobTrigger = new CronTriggerFactoryBean();
		dynamicJobTrigger.setName("dynamicTaskTrigger");
		dynamicJobTrigger.setJobDetail((JobDetail)dynamicJob.getObject());
		dynamicJobTrigger.setCronExpression("0/1 * * * * ?");
		dynamicJobTrigger.afterPropertiesSet();
		
		scheduler.scheduleJob((JobDetail) dynamicJob.getObject(), dynamicJobTrigger.getObject());
	}

}
