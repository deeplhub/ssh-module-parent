package com.xh.ssh.web.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.ssh.web.common.tool.Dom4jTool;
import com.xh.ssh.web.common.tool.LogTool;
import com.xh.ssh.web.mapper.model.WebLog;
import com.xh.ssh.web.service.log.service.IWebLogService;

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
public class WebLogServiceTest {

	@Resource
	private IWebLogService webLogService;

	// @Test
	public void save() {
		// WebLog log = new WebLog();
		// log.setId(1);
		// log.setMsgCode(this.getClass().getSimpleName());
		// log.setHolderName("BBB");
		// log.setOperateStatus(1);
		// log.setOperateType("1");
		// log.setUserId(0);
		// log.setFromIp("....");
		// log.setMsgFileIp("....");
		// log.setMsgPath("Path");
		// log.setMsgInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// log.setCreateTime(new Date());

		// webLogService.save(log);
	}

	@Test
	public void selectList() throws Exception {
		// 方法日志
		LogTool.info(this.getClass(), " selectList() ");

		// 参数
		WebLog webLog = new WebLog();
		webLog.setMsgCode("WebLogServiceTest");

		// 参数日志
		WebLog log = new WebLog(this.getClass().getSimpleName(), null, 1, "Test", null, null, null, null, webLog.toString());
		LogTool.info(this.getClass(), webLog.toString());
		webLogService.save(log);

		// 查询数据库
		// List<WebLog> list = webLogService.selectHqlList(webLog);
		List<WebLog> list = webLogService.selectBySql(webLog);
		// List<WebLog> list = webLogService.selectHqlListAll(webLog);

		System.out.println("====================================================");
		System.out.println(list.size());
		System.out.println("====================================================");

		String xml = Dom4jTool.parseFromListToXml(list);
		LogTool.info(this.getClass(), xml);

		// 结果日志
		WebLog log2 = new WebLog(this.getClass().getSimpleName(), null, 2, "Test", null, null, null, null, xml);
		webLogService.save(log2);

	}

}
