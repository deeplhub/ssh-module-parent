package com.xh.ssh.web.test;

import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年12月20日
 */
public class MainAssert {

	public static void main(String[] args) {
		WebTask task = new WebTask();
		System.out.println(task);
		
		task.setTaskName("AAA");
		System.out.println(task);
		
		WebTask task2 = new WebTask();
		task.setCron("BBBBB");
		System.out.println(task2);
	}

}
