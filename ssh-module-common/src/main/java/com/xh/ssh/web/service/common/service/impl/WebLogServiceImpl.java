package com.xh.ssh.web.service.common.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.ssh.web.mapper.dao.WebLogDao;
import com.xh.ssh.web.mapper.model.WebLog;
import com.xh.ssh.web.service.common.service.IWebLogService;

/**
 * <b>Title: 日志管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
@Service("com.xh.ssh.web.service.common.service.impl.webLogServiceImpl")
public class WebLogServiceImpl implements IWebLogService {

	@Autowired
	private WebLogDao webLogDao;

	@Override
	public void save(WebLog log) {
		log.setCreateTime(new Date());
		webLogDao.save(log);
	}

	@Override
	public List<WebLog> selectBySql(WebLog webLog) {

		return webLogDao.selectBySql(webLog);
	}

	@Override
	public List<WebLog> selectHqlList(WebLog webLog) {

		return webLogDao.selectHqlList(webLog);
	}

	@Override
	public List<WebLog> selectHqlListAll(WebLog webLog) {

		return webLogDao.selectHqlListAll(webLog);
	}

}
