package com.xh.ssh.web.test.task;

import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月6日
 */
public class MyTaskSericeImpl implements IDoTaskService {

	@Override
	public void exec(WebTask task) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(this.getClass().getSimpleName());
	}

}
