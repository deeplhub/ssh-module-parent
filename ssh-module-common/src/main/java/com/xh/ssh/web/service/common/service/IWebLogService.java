package com.xh.ssh.web.service.common.service;

import java.util.List;

import com.xh.ssh.web.mapper.model.WebLog;


/**
 * <b>Title: </b>
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
