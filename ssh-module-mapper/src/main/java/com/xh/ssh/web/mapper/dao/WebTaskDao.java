package com.xh.ssh.web.mapper.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.xh.ssh.web.mapper.dao.base.HibernateDaoImpl;
import com.xh.ssh.web.mapper.model.WebTask;

/**
 * <b>Title: 任务操作</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
@Repository
@SuppressWarnings("all")
public class WebTaskDao extends HibernateDaoImpl<WebTask, Long> {

	public void save(WebTask task) {
		super.saveObject(task);
	}

	public void delete(Integer paramId) {
		String hql = "DELETE FROM WebTask WHERE taskId = ?0 ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, paramId);
		query.executeUpdate();
	}

	public List<WebTask> selectAll() {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM WebTask ");

		return (List<WebTask>) super.loadTableByList(WebTask.class, sb);
	}
}
