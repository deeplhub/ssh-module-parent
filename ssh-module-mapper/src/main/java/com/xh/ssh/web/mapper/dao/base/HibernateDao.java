package com.xh.ssh.web.mapper.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * <b>Title: 基本DOA操作</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
@SuppressWarnings("hiding")
public interface HibernateDao<T, PK extends Serializable> {

	/**
	 * <b>Title: getSession</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @return
	 */
	public Session getSession();

	public <T> Object saveObject(T entity);

	public <T> Object updateObject(T entity);

	public <T> Object deleObject(T entity);

	public <T> void delObject(T entity);

	/**
	 * <b>Title: 根据指定实体类查询</b>
	 * <p>Description: 只返回一条数据</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramClass 实体类
	 * @param paramSerializableId ID
	 * @return
	 */
	public <T> Object loadObject(Class<T> paramClass, Serializable paramSerializableId);

	/**
	 * <b>Title: 根据指定实体类查询列表</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramClass 实体类
	 * @param paramString HQL语句
	 * @return
	 */
	public <T> List<T> loadTableByList(Class<T> paramClass, Serializable paramString);

	/**
	 * <b>Title: 根据指定实体类查询指定字段列表</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramClass 实体类
	 * @param paramMap 字段列
	 * @return
	 */
	public <T> T loadTableByCloumn(Class<T> paramClass, Map<Object, Object> paramMap);

	/**
	 * <b>Title: HQL查询</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString HQL语句
	 * @return
	 */
	public <T> List<T> selectByHql(String paramString);

	/**
	 * <b>Title: HQL查询</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString HQL语句
	 * @param paramArrayOfObject HQL查询参数
	 * @return
	 */
	public <T> List<T> selectByHql(String paramString, Object[] paramArrayOfObject);

	/**
	 * <b>Title: SQL查询</b>
	 * <p>Description: 返回指定类型Map|Entity</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL语句
	 * @param paramClass 返回类型 Map|Entity
	 * @return
	 */
	public <T> List<T> selectBySql(String paramString, Class<T> paramClass);

	/**
	 * <b>Title: SQL查询</b>
	 * <p>Description: 返回指定类型Map|Entity</p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL语句
	 * @param paramArrayOfObject SQL查询参数
	 * @param paramClass 返回类型 Map|Entity
	 * @return
	 */
	public <T> List<T> selectBySql(String paramString, Object[] paramArrayOfObject, Class<T> paramClass);

	/**
	 * <b>Title: SQL删除</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL查询参数
	 */
	public <T> void delObjectBySql(String paramString);

	/**
	 * <b>Title: SQL删除</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL查询参数
	 * @param paramArrayOfObject 要修改的参数
	 */
	public <T> void delObjectBySql(String paramString, Object[] paramArrayOfObject);

	/**
	 * <b>Title: 修改数据</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL语句
	 */
	public <T> void updateObjectBySql(String paramString);

	/**
	 * <b>Title: 修改数据</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL语句
	 * @param paramArrayOfObject 要修改的参数
	 */
	public <T> void updateObjectBySql(String paramString, Object[] paramArrayOfObject);

	/**
	 * <b>Title: 修改数据</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param paramString SQL语句
	 * @param paramList 要修改的参数
	 */
	public <T> void modifyBySqlAndList(String paramString, List paramList);

}
