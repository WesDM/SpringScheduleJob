package com.wesdm.springschedulejob.quartzscheduler;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

/*
 * Thread waits until latch count down to zero
 */
@Component("jobLatch")
public class QuartzJobLatch implements ILatch{
	private int repeatCount = 7;
	private CountDownLatch latch;
	
	public QuartzJobLatch() {
		System.out.println("Create count down latch for 3");
		latch = new CountDownLatch(repeatCount + 1);
	}

	public void countDown() {
		latch.countDown();
	}

	public void waitTillJobsExecute() throws InterruptedException {
		latch.await();  //Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
	}
}
