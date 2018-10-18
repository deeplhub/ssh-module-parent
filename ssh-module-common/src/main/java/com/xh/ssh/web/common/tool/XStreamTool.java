package com.xh.ssh.web.common.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * <b>Title: XML与Class之间相互转换</b>
 * <p>Description: </p>
 * <p>
 * XML转对象<br>
 * 对象转xml<br>
 * 
 * <br>
 * 
 * 优点：不显示冗余字段，方法简单。<br>
 * 缺点：对象转xml日期相差8小时；要引入jar包。
 * </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月22日
 */
public class XStreamTool {

	/**
	 * <b>Title: XML转对象</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	public static <T> T parseFromXmlToClass(String xml, Class<T> clazz) {
		// 创建解析XML对象
		XStream xStream = new XStream(new DomDriver());
		// 处理注解
		xStream.processAnnotations(clazz);
		// xStream.alias("WebLog", clazz);
		// 将XML字符串转为bean对象
		T t = (T) xStream.fromXML(xml);
		return t;
	}

	/**
	 * <b>Title: 对象转xml</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj
	 * @return
	 */
	public static String parseFromBeanToXml(Object obj) {
		XStream xStream = new XStream(new DomDriver());
		// xStream.alias("WebLog", obj.getClass());
		xStream.processAnnotations(obj.getClass());
		return xStream.toXML(obj);
	}

}
