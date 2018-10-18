package com.xh.ssh.web.common.tool;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @QQ 1033542070
 * @date 2018年3月13日
 */
public class GenEntityTableTest {

	public static void main(String[] args) {
//		GenEntityTable entity = new GenEntityTable();
//		entity.setPropertiesPath("resources/config/db.properties");
//		entity.setAuthorName("H.Yang");
//		entity.setPackageName("com.xh.ssh.web.mapper.model");
//		entity.setTableName("web_task");
//		entity.init();
//		entity.startTable();
		
		GenEntityTable entity = new GenEntityTable("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "admini");
		entity.setAuthorName("H.Yang");
		entity.setPackageName("com.xh.ssh.web.mapper.model");
		entity.setTableName("web_task");
		entity.startTable();
	}

}
