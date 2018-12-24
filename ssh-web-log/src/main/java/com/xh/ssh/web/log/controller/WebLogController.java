package com.xh.ssh.web.log.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("/easyui")
	public String indexEasyui() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
		return "easyui.index";
	}

	@RequestMapping("/query")
	@ResponseBody
	public Object queryList(WebLog webLog) {
		List<WebLog> list = webLogService.selectHqlList(webLog);
		return list;
	}
}
