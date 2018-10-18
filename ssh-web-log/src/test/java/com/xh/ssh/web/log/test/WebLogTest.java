package com.xh.ssh.web.log.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.ssh.web.mapper.model.WebLog;
import com.xh.ssh.web.service.service.IWebLogService;


/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class WebLogTest {

	@Resource
	private IWebLogService webLogService;

	// @Test
	public void save() {
		WebLog log = new WebLog();
		log.setFromIp("111");
		log.setMsgCode(this.getClass().getSimpleName());
		webLogService.save(log);
	}

	@Test
	public void selectList() {
		WebLog log = new WebLog();
		log.setMsgCode("WebLogTest");
		List<WebLog> list = webLogService.selectList(log);
		for (WebLog logs : list) {
			System.out.println(logs.toString());
		}
	}

}
