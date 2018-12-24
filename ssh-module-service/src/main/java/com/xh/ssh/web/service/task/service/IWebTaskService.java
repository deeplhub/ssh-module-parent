package com.xh.ssh.web.service.task.service;

import com.xh.ssh.web.mapper.model.WebTask;
import com.xh.ssh.web.service.base.service.IService;

/**
 * <b>Title: 任务管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
public interface IWebTaskService extends IService<WebTask> {

	public Object deleteById(String paramId);

}
