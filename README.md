## Maven多层项目

#### 版本： Hibernate5.3.1，Spring4.3.13



### 两大模块

### 一、日志系统

#### 1、Dom4jTool：

先引入dom4j.jar

> 优点：速度快，显示全部标签。<br>
> 缺点：不能灵活改变属性名称


参考地址：<br>
http://www.cnblogs.com/shitianzeng/articles/2518323.html


<br>
<br>
<br>



### 2、JAXBTool：

优点：速度快次于Dom4j，不显示冗余字段，使用JDK自带的。



JDK中JAXB相关的重要Class和Interface：

JAXBContext类，是应用的入口，用于管理XML/Java绑定信息。
Marshaller接口，将Java对象序列化为XML数据。
Unmarshaller接口，将XML数据反序列化为Java对象。


#### JDK中JAXB相关的重要Annotation：

```
@XmlType：将Java类或枚举类型映射到XML模式类型
@XmlAccessorType(XmlAccessType.FIELD)：控制字段或属性的序列化，定义映射这个类中的何种类型都需要映射到xml。(如果不存在@XmlAccessorType,默认使用XmlAccessType.PUBLIC_MEMBER注解)参数：
	XmlAccessType.FIELD：java对象中的所有成员变量，FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标注）字段到XML。
	XmlAccessType.PROPERTY：java对象中所有通过getter/setter方式访问的成员变量。
	XmlAccessType.PUBLIC_MEMBER：java对象中所有的public访问权限的成员变量和通过getter/setter方式访问的成员变量。
	XmlAccessType.NONE: java对象的所有属性都不映射为xml的元素。
@XmlAccessorOrder：控制JAXB 绑定类中属性和字段的排序。
@XmlJavaTypeAdapter：使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML。
@XmlElementWrapper：对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器）。
@XmlRootElement：将Java类或枚举类型映射到XML元素。
@XmlElement：将Java类的一个属性映射到与属性同名的一个XML元素。
@XmlAttribute：将Java类的一个属性映射到与属性同名的一个XML属性。

```

在以上的注解中，用的最多的是@XMLType，@XmlAccessorType，@XmlRootElement。


```
marshaller.setProperty(Marshaller.JAXB_ENCODING,"gb2312");//设置编码格式
marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串
marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）
marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "s");// 用来指定将放置在已编组 XML 输出中的 xsi:schemaLocation 属性值的属性名称
marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "w");// 用来指定将放置在已编组 XML 输出中的 xsi:noNamespaceSchemaLocation 属性值的属性名称

```

参考地址：<br>
https://blog.csdn.net/songdeitao/article/details/17304395


<br>
<br>
<br>




### 3、XStreamTool：

先引入xstream-1.4.10.jar

> 优点：不显示冗余字段，方法简单。<br>
> 缺点：对象转xml日期相差8小时；要引入jar包。


别名注解(方法与类同一个注解)：@XStreamAlias("")

如果在XML转换对象时使用别名，在对象转换XML时也要用别名。
XML转换对像时用注解的方式，一定要在XML转换对象时启用注解：xStream.processAnnotations(clazz);否则会报错。



XStream在实体类转换xml时如果用下划线会多一个下线线，此时用xmlStr.replace("__", "_")添加或去掉即可。

<br>
<br>
<br>

使用XStream时您需要

```
xstream-[version].jar，xpp3-[version].jar并 xmlpull-[version].jar
```

在类路径中。 Xpp3 是一种非常快速的XML拉解析器实现。如果您不想包含这些依赖项，则可以使用标准的JAXP DOM解析器，或者从Java 6开始使用集成的StAX解析器：

```
XStream xstream = new XStream（new DomDriver（））; //不需要XPP3库
XStream xstream = new XStream（new StaxDriver（））; //不需要从Java 6开始的XPP3库

```


参考地址：<br>
https://segmentfault.com/a/1190000012435867


<br>
<br>
<br>


### 二、定时任务系统

定时器管理<br>
创建要被定执行的任务类<br>
任务池<br>

<br>
<br>
<br>


#### 快速大小写转换：

```
public static void main(String[] args) throws Exception {
	String str = "abcdefg";
	char[] charArray = str.toCharArray();
	charArray[0] -= 32;
	charArray[1] -= 32;
	str = String.valueOf(charArray);
	System.out.println(str);
	
	charArray[0] += 32;
	charArray[1] += 32;
	str = String.valueOf(charArray);
	System.out.println(str);
}

```