package com.xh.ssh.web.mapper.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.xh.ssh.web.mapper.dao.base.HibernateDaoImpl;
import com.xh.ssh.web.mapper.model.WebLog;

/**
 * <b>Title: 日志操作</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
@Repository
@SuppressWarnings("all")
public class WebLogDao extends HibernateDaoImpl<WebLog, Long> {

	public void save(WebLog log) {
		super.saveObject(log);
	}

	public List<WebLog> selectBySql(WebLog webLog) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM web_log WHERE 1=1 ");
		ArrayList arrays = new ArrayList();

		if (webLog != null) {
			if (StringUtils.isNotBlank(webLog.getMsgCode())) {
				sb.append(" AND msg_code = ? ");
				arrays.add(webLog.getMsgCode());
			}
			if (StringUtils.isNotBlank(webLog.getOperateType())) {
				sb.append(" AND operate_type = ? ");
				arrays.add(webLog.getOperateType());
			}
			if (webLog.getOperateStatus() != null) {
				sb.append(" AND operate_status = ? ");
				arrays.add(webLog.getOperateStatus());
			}
		}
		//

		return super.selectBySql(sb.toString(), arrays.toArray(), WebLog.class);
	}

	public List<WebLog> selectHqlList(WebLog webLog) {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM WebLog WHERE 1=1");
		if (webLog != null) {
			if (StringUtils.isNotBlank(webLog.getMsgCode())) {
				sb.append(" AND msgCode = '");
				sb.append(webLog.getMsgCode() + "'");
			}
			if (StringUtils.isNotBlank(webLog.getOperateType())) {
				sb.append(" AND operateType = ");
				sb.append(webLog.getOperateType());
			}
			if (webLog.getOperateStatus() != null) {
				sb.append(" AND operateStatus = ");
				sb.append(webLog.getOperateStatus());
			}
		}

		return super.loadTableByList(sb);
	}

	public List<WebLog> selectHqlListAll(WebLog webLog) {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM WebLog WHERE 1=1");

		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}
}
