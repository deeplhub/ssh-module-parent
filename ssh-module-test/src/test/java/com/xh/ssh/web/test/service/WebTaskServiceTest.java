package com.xh.ssh.web.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.ssh.web.mapper.model.WebTask;
import com.xh.ssh.web.service.task.service.IWebTaskService;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class WebTaskServiceTest {

	@Resource
	private IWebTaskService taskService;

	// @Test
	public void query() {
		List<WebTask> list = taskService.selectAll();
		System.out.println(list.size());
	}

	@Test
	public void delete() {
		taskService.delete(1);
	}
}
