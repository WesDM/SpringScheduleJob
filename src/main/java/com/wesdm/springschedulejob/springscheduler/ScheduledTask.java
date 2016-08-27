package com.wesdm.springschedulejob.springscheduler;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("scheduledTask")
public class ScheduledTask{
	
	@Async
	public void doIt(){
		System.out.println("Doing It");
	}
	
	@Async
	public Future<String> returnSomething() throws InterruptedException {
		System.out.println("Doing Something");
	    Thread.sleep(5000);
	    return new AsyncResult<String>("Job is Done!");
	}
	
	@Async
	public void dynamicTask(){
		System.out.println("****************WOO!****************");
	}
}
