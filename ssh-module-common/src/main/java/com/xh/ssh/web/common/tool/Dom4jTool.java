package com.xh.ssh.web.common.tool;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <b>Title: XML与Class之间相互转换</b>
 * <p>Description: </p>
 * <p>
 * 对象转XML<br>
 * XML转指定类<br>
 * Document转对象<br>
 * List转XML<br>
 * XML转List<br>
 * 
 * <br>
 * 
 * 优点：速度快，显示全部标签。<br>
 * 缺点：不能灵活改变属性名称<br>
 * 
 * <br>
 * 
 * 大小写转换是用ASCII转换，这样速度快。例：<br>
 * String name = "abcd";<br>
 * char[] charArray = name.toCharArray();<br>
 * charArray[0] -= 32;<br>
 * name = String.valueOf(charArray);<br>
 * ......<br>
 * 结果：Abcd<br>
 * 
 * </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月22日
 */
@SuppressWarnings("all")
public class Dom4jTool {

	/**
	 * <b>Title: 对象转XML</b>
	 * <p>Description: Class and Map</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param object
	 * @return
	 */
	public static String parseFromBeanToXml(Object object) {
		try {
			Document document = DocumentHelper.createDocument();
			if (object instanceof Map) {
				// => MAP
				Map<String, Object> map = (Map<String, Object>) object;
				Element nodeElement = document.addElement("node");
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Element keyElement = nodeElement.addElement(entry.getKey());
					keyElement.setText(String.valueOf(entry.getValue()));
				}
				return document.asXML();
			} else {
				// 创建根节点元素
				Element root = document.addElement(object.getClass().getSimpleName());
				Field[] fields = object.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
				for (Field field : fields) {
					String name = field.getName();
					if (!name.equals("serialVersionUID")) {// 去除串行化序列属性
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, object.getClass());
						Method getMethod = propertyDescriptor.getReadMethod();
						Object value = getMethod.invoke(object);// 获取属性值

						Element element = root.addElement(name);
						element.setText((value != null) ? String.valueOf(value) : "");
					}
				}
				return document.asXML();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * <b>Title: XML转指定类</b>
	 * <p>Description: Class and Map</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param xmlStr
	 * @param clazz
	 * @return
	 */
	public static <T> T parseFromXmlToClass(String xmlStr, Class<T> clazz) {
		Object object = null;
		try {
			if (StringUtils.isEmpty(xmlStr)) {
				return null;
			}
			if (clazz == Map.class) {
				// 将xml格式的数据转换成Map对象
				object = new HashMap<String, Object>();
				// 将xml格式的字符串转换成Document对象
				Document doc = DocumentHelper.parseText(xmlStr);
				// 获取根节点
				Element root = doc.getRootElement();
				// 获取根节点下的所有元素
				List children = root.elements();
				// 循环所有子元素
				if (children != null && children.size() > 0) {
					Element element = null;
					for (Object obj : children) {
						element = (Element) obj;
						((Map) object).put(element.getName(), element.getTextTrim());
					}
				}
			} else {
				Document document = DocumentHelper.parseText(xmlStr);
				// Document转对象
				object = Dom4jTool.parseFromDocumentToCalss(document, clazz);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) object;
	}

	/**
	 * <b>Title: Document转对象</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param document
	 * @param clazz
	 * @return
	 */
	public static <T> T parseFromDocumentToCalss(Document document, Class<T> clazz) {
		Object obj = null;
		// 获取根节点
		Element root = document.getRootElement();
		try {
			obj = clazz.newInstance();// 创建对象
			List<Element> properties = root.elements();
			for (Element element : properties) {
				String name = element.getName();
				// 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
				Field field = BeanTool.getClassField(clazz, name);

				Method setMethod = new PropertyDescriptor(name, clazz).getWriteMethod();
				Object value = element.getText();
				value = ("".equals(value)) ? null : value;
				// 将Object类型的值，转换成bean对象属性里对应的类型值
				value = (value != null) ? BeanTool.toValType(value, field.getType()) : null;
				setMethod.invoke(obj, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * <b>Title: List转XML</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * @param <T>
	 * 
	 * @param list
	 * @return
	 */
	public static <T> String parseFromListToXml(List<T> list) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("rows");
		Map<Object, Object> map = null;
		for (T object : list) {
			// bean与Map
			map = (object instanceof Map) ? (Map<Object, Object>) object : BeanTool.parseFromBeanToMap(object, false);
			Element classElement = root.addElement(object.getClass().getSimpleName());
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				Element keyElement = classElement.addElement(entry.getKey().toString());
				keyElement.setText(String.valueOf(entry.getValue()));
			}
		}

		return document.asXML();
	}

	/**
	 * <b>Title: 根据指定的类来解析XML到List</b>
	 * <p>Description: 递归</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramXml
	 * @param clazz
	 * @return
	 * @throws DocumentException
	 */
	public static <T> List<T> parseFromXmlToList(String paramXml, Class<T> clazz) throws DocumentException {
		if (StringUtils.isEmpty(paramXml)) {
			return null;
		}
		// 将xml格式的字符串转换成Document对象
		Document document = DocumentHelper.parseText(paramXml);
		// 获取根节点(rows)
		Element root = document.getRootElement();
		List<T> list = new ArrayList<T>();
		// Element递归子级
		recursiveChildElement(root.elements(), list, clazz);
		return list;
	}

	/**
	 * <b>Title: Element递归子级</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param elements
	 * @param paramList
	 * @param clazz
	 */
	private static <T> void recursiveChildElement(List<Element> elements, List<T> paramList, Class<T> clazz) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : elements) {
			Element element = (Element) object;
			if (element.elements().size() > 1) {
				recursiveChildElement(((Element) object).elements(), paramList, clazz);
			} else {
				map.put(element.getName(), element.getTextTrim());
			}
		}

		if (!map.isEmpty()) {
			// 将map对象的数据转换成Bean对象
			Object obj = (clazz == Map.class) ? map : BeanTool.parseFromMapToClass(map, clazz);
			paramList.add((T) obj);
		}
	}

}
