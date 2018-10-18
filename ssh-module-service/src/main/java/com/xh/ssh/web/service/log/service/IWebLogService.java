package com.xh.ssh.web.service.log.service;

import java.util.List;

import com.xh.ssh.web.mapper.model.WebLog;


/**
 * <b>Title: 日志管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
public interface IWebLogService {

	public void save(WebLog log);
	
	public List<WebLog> selectBySql(WebLog webLog);
	
	public List<WebLog> selectHqlList(WebLog webLog);
	
	public List<WebLog> selectHqlListAll(WebLog webLog);
	
}
