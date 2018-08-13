package com.two.core.base.jdbc.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 * 功能描述:基础抽象
 * @param:
 * @return: 
 * @auther: 田杰
 * @date: 2018/4/21  
 */
public abstract class JpaDao<ID extends Serializable, E> implements
		IJpaDao<ID, E> {

	@PersistenceContext
	private EntityManager entityManager;

	protected final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}
	
	protected JpaDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	@Override
	public void persist(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public void remove(E entity) {
		entityManager.remove(entity);
	}

	@Override
	public void remove(ID id) {
		E entity = findById(id);
		if (entity != null) {
			remove(entity);
		}
	}
	
	public void removeByHql(String queryString,final Map<String, ?> params){
		Query queryObject = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryObject.setParameter(entry.getKey(), entry.getValue());
			}
		}
		queryObject.executeUpdate();
	}

	@Override
	public E merge(E entity) {
		entity = entityManager.merge(entity);
		return entity;
	}

	@Override
	public void refresh(E entity) {
		entityManager.refresh(entity);
	}

	@Override
	public E getReference(ID id) {
		return entityManager.getReference(entityClass, id);
	}

	@Override
	public E findById(ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public E flush(E entity) {
		entityManager.flush();
		return entity;
	}

	@Override
	public List<E> findAll() {
		return find("FROM " + entityClass.getName());
	}

	@Override
	public Integer removeAll() {
		Query q = entityManager.createQuery("DELETE FROM "
				+ entityClass.getName());
		return q.executeUpdate();
	}

	@Override
	public <T> List<T> find(String ql) {
		return find(ql, (Object[]) null);
	}

	@Override
	public <T> List<T> find(String ql, Object... values) {
		return find(false, ql, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(boolean cacheable, String ql, Object... values) {
		Query q = entityManager.createQuery(ql);
		if (cacheable) {
			q.setHint("org.hibernate.cacheable", true);
		}
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i + 1, values[i]);
			}
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParams(final String queryString,
			final Map<String, ?> params) {
		Query queryObject = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryObject.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return queryObject.getResultList();
	}
	
	public Long findCountByNamedParams(final String queryString,
			final Map<String, ?> params){
		Query queryObject = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryObject.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return (Long)queryObject.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T>  findByNamedParamsPage(String queryString, Map<String, ?> params,int pageSize, int pageNumber){
		Query queryObject = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryObject.setParameter(entry.getKey(), entry.getValue());
			}
		}
		queryObject.setFirstResult((pageNumber - 1) * pageSize);
		queryObject.setMaxResults(pageSize);
		return queryObject.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(String hql, int pageSize, int pageNumber,
			long patientId) {
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, patientId);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> nativeQuery(String sql, Map<String, Object> params) {
		Query q = entityManager.createNativeQuery(sql);
		setParameter(q, params);
		List<Object[]> list = q.getResultList();
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List queryNativeSql(String sql, Map<String, Object> params) {
		Query q = entityManager.createNativeQuery(sql);
		setParameter(q, params);
		List list = q.getResultList();
		return list;
	}
	
	private void setParameter(Query q, Map<String, Object> params) {
		if (q != null && params != null) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
	}
	
	public Integer nativeQueryCount(String sql, Map<String, Object> params) {
		Query q = entityManager.createNativeQuery(sql);
		setParameter(q, params);
		return Integer.valueOf(String.valueOf(q.getSingleResult()));
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> nativeQuery(String sql, Map<String, Object> params, int pageSize, int pageNumber) {
		Query q = entityManager.createNativeQuery(sql);
		q.setFirstResult((pageNumber - 1) * pageSize);
		q.setMaxResults(pageSize);		
		setParameter(q, params);
		List<Object[]> list = q.getResultList();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> hqlQuery(String hql, Map<String, Object> params,Class<T> clz, int pageSize, int pageNumber) {
		TypedQuery query = entityManager.createQuery(hql, clz);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);		
		setParameter(query, params);
		List<T> resultList = query.getResultList();
		return resultList;
	}
}
