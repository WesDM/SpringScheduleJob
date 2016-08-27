package com.wesdm.springschedulejob.quartzscheduler;

public class MyJobHelper {
	private String someStr;
	
	public MyJobHelper(String s) {
		this.someStr = s;
	}

	public String getSomeStr() {
		return someStr;
	}
}
