package com.xh.ssh.web.mapper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Title: web日志操作</p>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @QQ 1033542070
 * 
 * @date 2018-08-20
 */
// 控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标注）字段到XML。
// 其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
@XmlAccessorType(XmlAccessType.FIELD)
// 将Java类或枚举类型映射到XML元素。
@XmlRootElement(name = "WEB_LOG")
// @XStreamAlias("WebLog")
@Entity
@Table(name = "web_log")
public class WebLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// 消息编码
	@Column(name = "msg_code")
	private String msgCode;
	// 客户姓名
	@Column(name = "holder_name")
	private String holderName;
	// 状态
	@Column(name = "operate_status")
	private Integer operateStatus;
	// 类型
	@Column(name = "operate_type")
	private String operateType;
	// 用户ID
	@Column(name = "user_id")
	private Integer userId;
	// 请求IP
	@Column(name = "from_ip")
	private String fromIp;
	// 消息文件IP
	@Column(name = "msg_file_ip")
	private String msgFileIp;
	// 日志路径
	@Column(name = "msg_path")
	private String msgPath;
	// 日志内容
	@Column(name = "msg_info")
	private String msgInfo;
	// 创建时间
	@Column(name = "create_time")
	private Date createTime;

	public WebLog() {
		super();
	}
	
	public WebLog(String msgCode, String holderName, Integer operateStatus, String operateType, Integer userId, String fromIp,
			String msgFileIp, String msgPath, String msgInfo) {
		super();
		this.msgCode = msgCode;
		this.holderName = holderName;
		this.operateStatus = operateStatus;
		this.operateType = operateType;
		this.userId = userId;
		this.fromIp = fromIp;
		this.msgFileIp = msgFileIp;
		this.msgPath = msgPath;
		this.msgInfo = msgInfo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	/** 消息编码 */
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	/** 消息编码 */
	public String getMsgCode() {
		return msgCode;
	}

	/** 客户姓名 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	/** 客户姓名 */
	public String getHolderName() {
		return holderName;
	}

	/** 状态 */
	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}

	/** 状态 */
	public Integer getOperateStatus() {
		return operateStatus;
	}

	/** 类型 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/** 类型 */
	public String getOperateType() {
		return operateType;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 请求IP */
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	/** 请求IP */
	public String getFromIp() {
		return fromIp;
	}

	/** 消息文件IP */
	public void setMsgFileIp(String msgFileIp) {
		this.msgFileIp = msgFileIp;
	}

	/** 消息文件IP */
	public String getMsgFileIp() {
		return msgFileIp;
	}

	/** 日志路径 */
	public void setMsgPath(String msgPath) {
		this.msgPath = msgPath;
	}

	/** 日志路径 */
	public String getMsgPath() {
		return msgPath;
	}

	/** 日志内容 */
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	/** 日志内容 */
	public String getMsgInfo() {
		return msgInfo;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "WebLog [id=" + id + ", msgCode=" + msgCode + ", holderName=" + holderName + ", operateStatus=" + operateStatus
				+ ", operateType=" + operateType + ", userId=" + userId + ", fromIp=" + fromIp + ", msgFileIp=" + msgFileIp + ", msgPath="
				+ msgPath + ", msgInfo=" + msgInfo + ", createTime=" + createTime + "]";
	}

}
