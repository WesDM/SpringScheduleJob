package com.wesdm.springschedulejob;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wesdm.springschedulejob.quartzscheduler.QuartzJobLatch;

public class AppMain {
	public static void main(String args[]) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("quartz-context.xml");
		// Simple Trigger Used
//		try {
//			QuartzJobLatch latch = (QuartzJobLatch) context.getBean("jobLatch");
//			//latch.waitTillJobsExecute();
//			System.out.println("All triggers executed. Shutdown scheduler");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			//context.close();
//		}

		// Cron Trigger used
		// try {
		// Thread.sleep(15000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } finally {
		// context.close();
		// }
		// System.out.println(isValidSymbol(null,null));
		// System.out.println(isValidSymbol("Gold", "G"));
		// System.out.println(isValidSymbol("gold", "Gg"));
		// System.out.println(isValidSymbol("Gold", "gg"));
		//
		// String name = "Melintzum";
		// System.out.println(followsRules(name, "Nz"));
		// System.out.println(name);
		// String re = String.format("^[^%s]*%s{1}[^%s]*%s{1}[a-z]*$", "G", "G",
		// "g", "g");
		// System.out.println(re);
		// System.out.println("Gold".toLowerCase().matches(re));

	}

	public static boolean followsRules(String name, String symbol) {
		if (name == null || symbol == null) {
			throw new IllegalArgumentException();
		}
		if (name.isEmpty() || symbol.length() != 2) {
			return false;
		}
		if (!name.matches("^[A-Z]{1}[a-z]+$") || !symbol.matches("^[A-Z]{1}[a-z]+$")) {
			return false;
		}

		name = name.toLowerCase();
		symbol = symbol.toLowerCase();

		if (name.indexOf(symbol.charAt(0)) == -1) {
			return false;
		}

		return name.indexOf(symbol.charAt(0)) < name.indexOf(symbol.charAt(1));
	}

}
