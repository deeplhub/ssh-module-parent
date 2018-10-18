package com.xh.ssh.web.service.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.ssh.web.mapper.dao.WebTaskDao;
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
@Service
public class WebTaskServiceImpl implements IWebTaskService {

	@Autowired
	private WebTaskDao webTaskDao;

	@Override
	public void save(WebTask task) {
		webTaskDao.save(task);
	}

	@Override
	public void update(WebTask task) {
		webTaskDao.updateObject(task);
	}

	@Override
	public void delete(Integer paramId) {
		webTaskDao.delete(paramId);
	}

	@Override
	public WebTask get(Integer paramId) {

		return (WebTask) webTaskDao.loadObject(WebTask.class, paramId);
	}

	@Override
	public List<WebTask> selectAll() {

		return webTaskDao.selectAll();
	}

}
