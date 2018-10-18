package com.xh.ssh.web.test.common;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xh.ssh.web.mapper.model.WebLog;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月28日
 */
@XmlRootElement(name = "list")
public class WebLogList {

	@XmlElement(name = "WebLog")
	private List<WebLog> WebLogList;

	public List<WebLog> getWebLogList() {
		return WebLogList;
	}

	public void setWebLogList(List<WebLog> webLogList) {
		WebLogList = webLogList;
	}

}
