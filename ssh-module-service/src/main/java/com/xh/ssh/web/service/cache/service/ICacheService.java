package com.xh.ssh.web.service.cache.service;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年10月23日
 */
public interface ICacheService {

	/**
	 * <b>Title: 加载表到缓存</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param beanName 要扫描的类
	 */
	public void loadTableToCacheAll(String beanName);

}
