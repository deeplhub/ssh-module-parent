package com.xh.ssh.web.log.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.xh.ssh.web.mapper.model.WebLog;
import com.xh.ssh.web.service.log.service.IWebLogService;

/**
 * <b>Title: 日志管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月21日
 */
@Controller
public class WebLogController {

	@Autowired
	private IWebLogService webLogService;

	@RequestMapping("info")
	public String index() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
		return "info";
	}

	public void save(String paramXML) {

		WebLog webLog = null;
		webLogService.save(webLog);
	}

	@RequestMapping("query")
	public String queryList(WebLog webLog) {
		List<WebLog> list = webLogService.selectHqlList(webLog);
		String json = JSON.toJSONString(list);
		return json;
	}
}
