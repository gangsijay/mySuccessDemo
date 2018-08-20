package com.two.core.base.jdbc.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * @param: 
 * @return: 
 * @auther: 田杰
 * @date: 2018/4/21  
 */
public interface IJpaDao<ID extends Serializable, E> {

	void persist(E entity);

	void remove(E entity);

	void remove(ID id);

	E merge(E entity);

	void refresh(E entity);

	E findById(ID id);

	E flush(E entity);

	List<E> findAll();

	Integer removeAll();

	E getReference(ID id);

	<T> List<T> find(String ql);

	<T> List<T> find(String ql, Object... values);

	<T> List<T> find(boolean cacheable, String ql, Object... values);

	<T> List<T> findByNamedParams(String queryString, Map<String, ?> params);
	
	Long findCountByNamedParams(final String queryString,
                                final Map<String, ?> params);

	<T> List<T> findByNamedParamsPage(String queryString, Map<String, ?> params, int pageSize, int pageNumber);
	
	<T> List<T> find(String hql, int pageSize, int pageNumber, long patientId);
	
	List<Object[]> nativeQuery(String sql, Map<String, Object> params);
	
	public void removeByHql(String queryString, final Map<String, ?> params);

}