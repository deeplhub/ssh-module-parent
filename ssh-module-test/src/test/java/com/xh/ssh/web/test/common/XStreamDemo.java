package com.xh.ssh.web.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xh.ssh.web.common.tool.XStreamTool;
import com.xh.ssh.web.mapper.model.WebLog;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月27日
 */
public class XStreamDemo {

	public static void main(String[] args) {
//		WebLog log = new WebLog();
//		log.setId(1);
//		log.setMsgCode("AAAAAAAAA");
//		log.setHolderName("BBB");
//		// log.setOperateStatus(1);
//		// log.setOperateType("1");
//		// log.setUserId(0);
//		// log.setFromIp("....");
//		// log.setMsgFileIp("....");
//		log.setMsgPath("Path");
//		log.setMsgInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//		log.setCreateTime(new Date());

		// => 对象转xml
//		String xmlStr = XStreamTool.parseFromBeanToXml(log);
//		// xmlStr = xmlStr.replace("__", "_");
//		System.out.println(xmlStr);
//
//		// xmlStr = xmlStr.replace("_", "__");
//		// System.out.println(xmlStr);
//
//		// => XML转对象
//		log = XStreamTool.parseFromXmlToClass(xmlStr, WebLog.class);
//		System.out.println(log.toString());
		
		
		String xmlStr = XStreamTool.parseFromBeanToXml(getMapList());
		System.out.println(xmlStr);
	}

	public static List<Map<String, Object>> getMapList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "AAA");
		map.put("age", 23);
		map.put("sex", 1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("name", "BBB");
		map.put("age", 24);
		map.put("sex", 1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("name", "CCC");
		map.put("age", 25);
		map.put("sex", 1);
		list.add(map);

		return list;
	}

	public static List<WebLog> getList() {
		List<WebLog> list = new ArrayList<WebLog>();
		WebLog log = new WebLog();
		log.setId(1);
		log.setMsgCode("AAAAAAAAA");
		log.setHolderName("ABC");
		log.setOperateStatus(1);
		log.setOperateType("1");
		log.setUserId(0);
		log.setFromIp("....");
		log.setMsgFileIp("....");
		log.setMsgPath("Path");
		log.setMsgInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		log.setCreateTime(new Date());
		list.add(log);

		log = new WebLog();
		log.setId(2);
		log.setMsgCode("BBBBBBBBBBB");
		log.setHolderName("BAC");
		log.setOperateStatus(1);
		log.setOperateType("1");
		log.setUserId(0);
		log.setFromIp("....");
		log.setMsgFileIp("....");
		log.setMsgPath("Path");
		log.setMsgInfo("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		log.setCreateTime(new Date());
		list.add(log);

		log = new WebLog();
		log.setId(3);
		log.setMsgCode("CCCCCCCCCCC");
		log.setHolderName("CAB");
		log.setOperateStatus(1);
		log.setOperateType("1");
		log.setUserId(0);
		log.setFromIp("....");
		log.setMsgFileIp("....");
		log.setMsgPath("Path");
		log.setMsgInfo("CCCCCCCCCCCCCCCCCCCCCCCCCCC");
		log.setCreateTime(new Date());
		list.add(log);

		return list;
	}
}
