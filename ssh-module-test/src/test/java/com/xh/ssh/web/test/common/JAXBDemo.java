package com.xh.ssh.web.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xh.ssh.web.common.tool.JAXBTool;
import com.xh.ssh.web.mapper.model.WebLog;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月27日
 */
public class JAXBDemo {

	public static void main(String[] args) throws Exception {
		//
		// WebLog log = new WebLog();
		// log.setId(1);
		// log.setMsgCode("AAA");
		// log.setHolderName("BBB");
		// log.setOperateStatus(1);
		// // log.setOperateType("1");
		// // log.setUserId(0);
		// // log.setFromIp("1");
		// // log.setMsgFileIp("1");
		// log.setMsgPath("path");
		// log.setMsgInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// log.setCreateTime(new Date());

		// System.out.println(JAXBTool.marshal(log));
		// System.out.println();
		// System.out.println(JAXBTool.marshal(log, "GBK"));
		// System.out.println();
		// System.out.println(JAXBTool.unmarshalXml(JAXBTool.marshal(log, "GBK"), WebLog.class));
		// System.out.println();
		// JAXBTool.parseFromBean(log, "D:/下载/xml.xml");

		WebLogList webLogList = new WebLogList();
		webLogList.setWebLogList(getList());
		String xmlStr = JAXBTool.marshal(webLogList);
		System.out.println(xmlStr);
		System.out.println();
		webLogList = JAXBTool.unmarshal(xmlStr, WebLogList.class);
		for (WebLog log : webLogList.getWebLogList()) {
			System.out.println(log.toString());
		}
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
