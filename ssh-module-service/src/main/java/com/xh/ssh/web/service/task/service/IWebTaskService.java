package com.xh.ssh.web.service.task.service;

import java.util.List;

import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: 任务管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
public interface IWebTaskService {

	public void save(WebTask task);
	
	public void update(WebTask task);
	
	public void delete(Integer paramId);
	
	public WebTask get(Integer paramId);
	
	public List<WebTask> selectAll();

}
