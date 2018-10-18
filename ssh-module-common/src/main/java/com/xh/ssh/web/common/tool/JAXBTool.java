package com.xh.ssh.web.common.tool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * <b>Title: XML与Class之间相互转换</b>
 * <p>Description: </p>
 * <p>
 * XML转换成对象<br>
 * 将XML数据反序列化为Java对象(抛异常，速度快)<br>
 * 将Java对象序列化为XML数据（默认UTF-8）<br>
 * 将Java对象序列化为XML数据（指定编码格式）<br>
 * 将Java对象序列化为XML数据（抛异常）<br>
 * 将对象转换成xml,并保存文件<br>
 * 将file类型的xml转换成对象<br>
 * 返回原生Marshaller<br>
 * 返回原生Unmarshaller<br>
 * 
 * <br>
 * 
 * 优点：速度快次于Dom4j，不显示冗余字段。
 * </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月22日
 */
public class JAXBTool {

	/**
	 * <b>Title: XML转换成对象</b>
	 * <p>Description: 这种知道就可以了</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param clazz
	 * @param xmlStr
	 * @return
	 */
	@Deprecated
	public static <T> T unmarshalXml(String xmlStr, Class<T> clazz) {
		Object obj = null;
		try {
			Unmarshaller unmarshaller = JAXBTool.getUnmarshaller(clazz);
			StringReader sr = new StringReader(xmlStr);
			obj = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * <b>Title: 将XML数据反序列化为Java对象（抛异常）。</b>
	 * <p>Description: 速度快</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param xml
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static <T> T unmarshal(String xml, Class<T> clazz) throws Exception {
		StringReader reader = null;
		try {
			Unmarshaller unmarshaller = JAXBTool.getUnmarshaller(clazz);
			reader = new StringReader(xml);
			JAXBElement<? extends Object> root = unmarshaller.unmarshal(new StreamSource(reader), clazz);
			return (T) root.getValue();
		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * <b>Title: 将Java对象序列化为XML数据（默认）。</b>
	 * <p>Description: UTF-8</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj 实体类
	 * @return
	 */
	public static String marshal(Object obj) {
		try {
			return JAXBTool.marshal(obj, Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <b>Title: 将Java对象序列化为XML数据。</b>
	 * <p>Description: 指定编码格式</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj 实体类
	 * @param encoding 编码格式
	 * @return
	 */
	public static String marshal(Object obj, Object encoding) {
		try {
			return JAXBTool.marshal(obj, Marshaller.JAXB_ENCODING, encoding);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <b>Title: 将Java对象序列化为XML数据</b>
	 * <p>Description: Marshaller Property 属性配置：<br>
	 * Marshaller.JAXB_ENCODING, "gb2312" 设置编码格式<br>
	 * Marshaller.JAXB_FORMATTED_OUTPUT, true 是否格式化生成的xml串<br>
	 * Marshaller.JAXB_FRAGMENT, false 是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）<br>
	 * Marshaller.JAXB_SCHEMA_LOCATION, "s" 用来指定将放置在已编组 XML 输出中的 xsi:schemaLocation 属性值的属性名称<br>
	 * Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "w" 用来指定将放置在已编组 XML 输出中的 xsi:noNamespaceSchemaLocation 属性值的属性名称<br>
	 * </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj 实体类
	 * @param name 属性配置<br>
	 * @param value 属性值
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static String marshal(Object obj, String name, Object value) throws IOException, JAXBException {
		Writer writer = null;
		try {
			writer = new StringWriter();
			Marshaller marshaller = JAXBTool.getMarshal(obj, name, value);

			marshaller.marshal(obj, writer);
			// 将对象转换成输出流形式的xml
			return writer.toString();
		} catch (JAXBException e) {
			throw e;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * <b>Title: 将对象转换成xml,并保存文件</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj
	 * @param path
	 */
	public static void parseFromBean(Object obj, String path) {
		try {
			Marshaller marshaller = JAXBTool.getMarshal(obj, Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			// 创建输出流
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>Title: 将file类型的xml转换成对象</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param clazz
	 * @param xmlPath
	 * @return
	 */
	public static <T> T parseFromClass(Class<T> clazz, String xmlPath) {
		Object obj = null;
		try {
			Unmarshaller unmarshaller = getUnmarshaller(clazz);
			FileReader fr = null;
			try {
				fr = new FileReader(xmlPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			obj = unmarshaller.unmarshal(fr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * <b>Title: 返回原生Marshaller</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param obj
	 * @param name
	 * @param value
	 * @param marshaller
	 * @return 
	 * @throws JAXBException
	 */
	public static Marshaller getMarshal(Object obj, String name, Object value) throws JAXBException {
		// 利用jdk中自带的转换类实现
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		// 格式化xml输出的格式
		marshaller.setProperty(name, value);
		return marshaller;
	}

	/**
	 * <b>Title: 返回原生Unmarshaller</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param clazz
	 * @param xmlStr
	 * @param unmarshaller
	 * @throws JAXBException
	 */
	public static Unmarshaller getUnmarshaller(Class clazz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		// 进行将Xml转成对象的核心接口
		return context.createUnmarshaller();
	}

}
