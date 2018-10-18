package com.xh.ssh.web.test.common;

import com.xh.ssh.web.common.tool.JedisClientTool;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月1日
 */
public class JedisClientDemo {

	public static void main(String[] args) {
		String key = "Str", //
				value = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		JedisClientTool.set(key, value);
		JedisClientTool.expire(key, 60);

		// String val = (String) JedisClientTool.get(key);
		// System.out.println(val);
	}
}
