package com.xh.ssh.web.mapper.dao.cache;

import java.util.Map;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年10月25日
 */
public interface ICacheDao {

	public Map<Object, Object> loadTableToCache();
}
