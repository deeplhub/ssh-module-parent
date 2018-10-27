package com.xh.ssh.web.mapper.dao.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * <b>Title: 基本DOA操作</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年8月20日
 */
@SuppressWarnings("all")
public class HibernateDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IHibernateDao<T, Serializable> {

	@Resource(name = "sessionFactory")
	private void setMySessionFactory(SessionFactory sessionFactory) {

		// 这个方法名可以随便写，@Resource可以通过name 或者type来装载的。
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public <T> Object saveObject(T entity) {
		Assert.notNull(entity, "实体类不能为空");
		return getHibernateTemplate().save(entity);
	}

	@Override
	public <T> Object updateObject(T entity) {
		Assert.notNull(entity, "实体类不能为空");
		getHibernateTemplate().update(entity);
		return entity;
	}

	@Override
	public <T> Object deleteObject(T entity) {
		Assert.notNull(entity, "实体类不能为空");
		getHibernateTemplate().delete(entity);
		return entity;
	}

	@Override
	public <T> List<T> loadAll(Class<T> paramClass) {

		return getHibernateTemplate().loadAll(paramClass);
	}

	@Override
	public <T> Object getObject(String entityName, Serializable paramSerializableId) {

		return getHibernateTemplate().get(entityName, paramSerializableId);
	}

	@Override
	public <T> Object loadObject(Class<T> paramClass, Serializable paramSerializableId) {

		return getHibernateTemplate().get(paramClass, paramSerializableId);
	}

	@Override
	public <T> List<T> loadTableByList(Serializable hql) {

		return getSession().createQuery(hql.toString()).list();
	}

	@Override
	public <T> T loadTableByCloumn(Class<T> paramClass, Map<Object, Object> paramMap) {
		StringBuffer hql = new StringBuffer("FROM " + paramClass.getName() + " t where 1=1 ");
		return (T) getSession().createQuery(buildHQL(paramMap, hql).toString()).uniqueResult();
	}

	@Override
	public <T> List<T> selectTableByCloumn(Class<T> paramClass, Map<Object, Object> paramMap) {
		StringBuffer hql = new StringBuffer("FROM " + paramClass.getName() + " t where 1=1 ");
		return (List<T>) getSession().createQuery(buildHQL(paramMap, hql).toString()).list();
	}

	@Override
	public <T> List<T> selectByHql(String paramString) {

		return this.selectByHql(paramString, null);
	}

	@Override
	public <T> List<T> selectByHql(String paramString, Object[] paramArrayOfObject) {

		if ((null == paramArrayOfObject) || (paramArrayOfObject.length == 0)) {
			return (List<T>) getHibernateTemplate().find(paramString);
		}
		return (List<T>) getHibernateTemplate().find(paramString, paramArrayOfObject);
	}

	@Override
	public <T> List<T> selectBySql(String paramString, Class<T> paramClass) {

		return this.selectBySql(paramString, null, paramClass);
	}

	@Override
	public <T> List<T> selectBySql(String paramString, Object[] paramArrayOfObject, Class<T> paramClass) {

		NativeQuery<T> query = null;
		if (paramClass.getName().equals(Map.class.getName())) {
			query = getSession().createNativeQuery(paramString);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		} else {
			if (paramClass != null) {
				query = getSession().createNativeQuery(paramString, paramClass);
			} else {
				query = getSession().createNativeQuery(paramString);
			}
		}

		if (null != paramArrayOfObject) {
			for (int i = 0; i < paramArrayOfObject.length; i++) {
				query.setParameter(i + 1, paramArrayOfObject[i]);
			}
		}
		return query.list();
	}

	@Override
	public <T> void delObjectBySql(String paramString) {
		this.modifyBySql(paramString, null);
	}

	@Override
	public <T> void delObjectBySql(String paramString, Object[] paramArrayOfObject) {
		this.modifyBySql(paramString, paramArrayOfObject);
	}

	@Override
	public <T> void updateObjectBySql(String paramString) {
		this.modifyBySql(paramString, null);
	}

	@Override
	public <T> void updateObjectBySql(String paramString, Object[] paramArrayOfObject) {
		this.modifyBySql(paramString, paramArrayOfObject);
	}

	@Override
	public <T> void modifyBySqlAndList(String paramString, List paramList) {
		NativeQuery<T> query = getSession().createNativeQuery(paramString);
		if (!paramList.isEmpty()) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i + 1, paramList.get(i));
			}
		}
		query.executeUpdate();
	}

	@Override
	public <T> List<Object[]> selectBySQLArray(String paramString) {

		return this.selectBySQLArray(paramString, null);
	}

	@Override
	public <T> List<Object[]> selectBySQLArray(String paramString, Object[] paramArrayOfObject) {
		NativeQuery query = getSession().createNativeQuery(paramString);
		if (null != paramArrayOfObject) {
			for (int i = 0; i < paramArrayOfObject.length; i++) {
				query.setParameter(i + 1, paramArrayOfObject[i]);
			}
		}
		return query.list();
	}

	/********************************************************************************
	 * 
	 * 
	 * 私有方法
	 * 
	 * 
	 ********************************************************************************/

	private <T> void modifyBySql(String paramString, Object[] paramArrayOfObject) {
		NativeQuery<T> query = getSession().createNativeQuery(paramString);
		if (null != paramArrayOfObject) {
			for (int i = 0; i < paramArrayOfObject.length; i++) {
				System.out.println(paramArrayOfObject[i]);
				query.setParameter(i + 1, paramArrayOfObject[i]);
			}
		}
		query.executeUpdate();
	}

	private StringBuffer buildHQL(Map<Object, Object> map, StringBuffer hql) {
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (val instanceof String) {
				hql.append(" AND t." + key + " = '" + val + "' ");
			} else {
				hql.append(" AND t." + key + " = " + val + " ");
			}
		}
		return hql;
	}

}
