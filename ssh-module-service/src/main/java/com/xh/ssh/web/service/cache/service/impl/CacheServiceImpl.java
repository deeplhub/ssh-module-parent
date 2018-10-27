package com.xh.ssh.web.service.cache.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xh.ssh.web.common.tool.JedisClientTool;
import com.xh.ssh.web.common.tool.LogTool;
import com.xh.ssh.web.common.tool.SpringTool;
import com.xh.ssh.web.mapper.dao.cache.ICacheDao;
import com.xh.ssh.web.service.cache.service.ICacheService;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年10月25日
 */
@Service
public class CacheServiceImpl implements ICacheService {

	@Resource
	private ICacheDao cacheDao;

	private static final String DAO_SUFFIX = "Dao";

	@Override
	public void loadTableToCacheAll(String beanName) {
		if (!beanName.endsWith(DAO_SUFFIX)) {
			beanName = beanName + DAO_SUFFIX;
		}
		cacheDao = SpringTool.getSpringBeanThrows(beanName);
		Map<Object, Object> resultMap = cacheDao.loadTableToCache();

		// 删除无用的KEY
		if (beanName.endsWith(DAO_SUFFIX)) {
			beanName = beanName.replaceAll(DAO_SUFFIX, "");
		}
		beanName = StringUtils.capitalize(beanName);// 首字母大写
		String redisKey = beanName + ":*";

		Set<String> resultSet = JedisClientTool.getKeys(redisKey);
		Iterator<String> it = resultSet.iterator();
		while (it.hasNext()) {
			if (resultMap.get(it.next()) != null) {
				it.remove();
			}
		}
		String[] arrs = resultSet.toArray(new String[resultSet.size()]);
		JedisClientTool.del(arrs);

		// 加入缓存
		for (Map.Entry<Object, Object> entry : resultMap.entrySet()) {
			JedisClientTool.set(entry.getKey().toString(), entry.getValue().toString());
		}

		LogTool.info(this.getClass(), " Load table to cache all success... ");
	}

}
