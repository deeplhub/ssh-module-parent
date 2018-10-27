package com.xh.ssh.web.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
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
		// List<WebTask> list = taskService.selectAll();
		List<WebTask> list = taskService.loadAll(WebTask.class);
		System.out.println(list.size());
	}

	// @Test
	public void delete() {
		taskService.delete(1);
	}

	// @Test
	public void getAll() {
		WebTask task = new WebTask();
		task.setTaskId(1);

		// task = (WebTask) taskService.load(WebTask.class, 1);
		// task = (WebTask) taskService.get("com.xh.ssh.web.mapper.model.WebTask", 1);

		// Map<Object, Object> paramMap = new HashMap<Object, Object>();
		// paramMap.put("taskId", 1);
		// task = taskService.loadTableByCloumn(WebTask.class, paramMap);
		task = (WebTask) taskService.loadTableByCloumn(task);

		System.out.println(JSON.toJSONString(task));
	}

	// @Test
	public void get() {
		String taskId = "1";
		WebTask task = (WebTask) taskService.load(WebTask.class, Integer.valueOf(taskId));

		System.out.println(JSON.toJSONString(task));
	}

	@Test
	public void getArr() {

//		List<Object[]>  arrs = taskService.selectArray();
//		
//		System.out.println(JSON.toJSONString(arrs));
		
	}
}
