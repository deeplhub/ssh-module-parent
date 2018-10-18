package com.xh.ssh.web.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xh.ssh.web.common.tool.Dom4jTool;
import com.xh.ssh.web.mapper.model.WebLog;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月27日
 */
public class Dom4jDemo {

	public static void main(String[] args) throws Exception {
		WebLog log = new WebLog();
		log.setId(1);
		log.setMsgCode("AAAAAAAAA");
		log.setHolderName("BBB");
		log.setOperateStatus(1);
		log.setOperateType("1");
		log.setUserId(0);
		log.setFromIp("....");
		log.setMsgFileIp("....");
		log.setMsgPath("Path");
		log.setMsgInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// log.setCreateTime(new Date());
		// => 对象转xml格式的字符串
		String xml = Dom4jTool.parseFromBeanToXml(log);
		System.out.println(xml);

		// => 将xml格式的字符串转换成Map对象
		Map<String, Object> map = Dom4jTool.parseFromXmlToClass(xml, Map.class);
		System.out.println("\n\nMAP => " + map.toString());

		// // => xml字符串转换成bean对象
		// log = Dom4jTool.parseFromXmlToClass(xml, WebLog.class);
		// System.out.println(log.toString());
		//
		// => Map转XML
		xml = Dom4jTool.parseFromBeanToXml(map);
		System.out.println("\n\nXML => " + xml);
		//
		// long startTime = 0, //
		// endTime = 0, //
		// size = 10000;
		//
		// startTime = System.currentTimeMillis();
		// for (int i = 0; i < size; i++) {
		// Dom4jTool.parseFromXmlToClass(xml, WebLog.class);
		// }
		// endTime = System.currentTimeMillis();
		// System.out.println(endTime - startTime);
		// System.out.println();
		// System.out.println();
		//
		// startTime = System.currentTimeMillis();
		// for (int i = 0; i < size; i++) {
		// Dom4jTool.parseFromXmlToClass(xml, WebLog.class);
		// }
		// endTime = System.currentTimeMillis();
		// System.out.println(endTime - startTime);

		// String date = "Tue Aug 28 16:43:09 CST 2018";
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
		// System.out.println(sdf.format(sfStart.parse(date)));

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
