package com.xh.ssh.web.service.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xh.ssh.web.mapper.dao.WebTaskDao;
import com.xh.ssh.web.mapper.model.WebTask;
import com.xh.ssh.web.service.base.service.impl.ServiceImpl;
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
public class WebTaskServiceImpl extends ServiceImpl<WebTaskDao, WebTask> implements IWebTaskService {

	@Resource
	private WebTaskDao webTaskDao;

	@Override
	public Object deleteById(String paramId) {

		webTaskDao.deleteById(paramId);

		String name = "";
		List<WebTask> list = webTaskDao.selectByHql(paramId);
		for (WebTask webTask : list) {
			name += webTask.getTaskName() + ", ";
		}
		return name;
	}

}
