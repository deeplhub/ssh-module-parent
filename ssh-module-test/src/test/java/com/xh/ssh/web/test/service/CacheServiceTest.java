package com.xh.ssh.web.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.ssh.web.service.cache.service.ICacheService;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年10月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class CacheServiceTest {

	@Resource
	private ICacheService cacheService;

	// 刷新缓存（缓存表中所有可用数据）
	@Test
	public void refreshCache() {
		String beanName = "webTask";
		cacheService.loadTableToCacheAll(beanName);

	}

}
