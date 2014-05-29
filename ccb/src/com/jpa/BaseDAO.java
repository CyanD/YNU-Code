package com.jpa;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.core.paging.Page;

import com.util.GenericsUtils;

@Transactional
public class BaseDAO<T> {
	protected static org.apache.log4j.Logger log = org.apache.log4j.LogManager
			.getLogger(BaseDAO.class);

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) GenericsUtils
			.getSuperClassGenricType(this.getClass());

	@PersistenceContext
	protected EntityManager em;

	public BaseDAO() {
	}

	public void clear() {
		em.clear();
	}

	public void delete(Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		return em.find(entityClass, entityId);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <E> E find(Class<E> tclass, Serializable entityId) {
		if (entityId == null)
			throw new RuntimeException(this.entityClass.getName()
					+ ":传入的实体id不能为空");
		return em.find(tclass, entityId);
	}

	
	public void save(T entity) {
		em.persist(entity);
	}
	
	public void saves(List<T> entitys) {
		
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public int getCount() {
		return (Integer) em.createQuery(
				"select count(" + getCountField(this.entityClass) + ") from "
						+ getEntityName(this.entityClass) + " o")
				.getSingleResult();
	}

	public int getCount(String clause) {
		return getCount(entityClass, clause);
	}

	public <E> int getCount(Class<E> tclass, String clause) {

		StringBuilder sb = new StringBuilder();
		sb.append("select count(").append(getCountField(tclass));
		sb.append(") from ").append(getEntityName(tclass));
		sb.append(" o ");
		if (clause != null && !"".equals(clause)) {			
			sb.append(clause);
		}

		Object obj = em.createQuery(sb.toString()).getSingleResult();
		
		return Integer.parseInt(obj.toString()) ;
	}

	public void update(T entity) {
		em.merge(entity);
	}

	/**
	 * 获取实体的名称
	 * 
	 * @param <E>
	 * @param clazz
	 *            实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}

	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,
	 * hibernate对此jpql解析后的sql为select
	 * count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * 
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector
					.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if (method != null
						&& method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							propertydesc.getPropertyType())
							.getPropertyDescriptors();
					out = "o."
							+ propertydesc.getName()
							+ "."
							+ (!ps[1].getName().equals("class") ? ps[1]
									.getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public List<T> findPageByClasuse(Page page, String whereClause) {

		return findPageByClasuse(this.entityClass, page, whereClause);
	}

	public List<T> findByClause(String whereClause) {

		return findByClause(entityClass, whereClause);
	}

	public T findOneByClause(String whereClause) {
		return findOneByClause(entityClass, whereClause);
	}

	public <E> List<E> findPageByClasuse(Class<E> tclass, Page page,
			String whereClause) {
		String entityname = getEntityName(tclass);
		String queryString = "select t from " + entityname + " as t ";
		if (whereClause != null && !"".equals(whereClause)) {
			queryString = queryString + whereClause;
		}
		List<E> result = null;

		Query q = em.createQuery(queryString);
		q.setFirstResult(page.getBegin());
		q.setMaxResults(page.getEnd());
		result = q.getResultList();

		return result;
	}

	public <E> List<E> findByClause(Class<E> tclass, String whereClause) {
		String entityname = getEntityName(tclass);
		String queryString = "select t from " + entityname + " as t ";
		if (whereClause != null && !"".equals(whereClause)) {
			queryString = queryString + whereClause;
		}
		List<E> result = null;

		Query q = em.createQuery(queryString);
		result = q.getResultList();
		return result;
	}

	public <E> E findOneByClause(Class<E> tclass, String whereClause) {
		String entityname = getEntityName(tclass);
		String queryString = "select t from " + entityname + " as t ";
		if (whereClause != null && !"".equals(whereClause)) {
			queryString = queryString + whereClause;
		}
		List<E> result = null;

		Query q = em.createQuery(queryString);
		result = q.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	
	public void deleteByClause(String whereClause) {
		String entityname = getEntityName(this.entityClass);
		deleteByClause(whereClause,entityname);
		
	}
	
	public void deleteByClause(String whereClause,String entityname){		
		String queryString = "delete from " + entityname + "";
		if (whereClause != null && !"".equals(whereClause)) {
			queryString = queryString + " " +whereClause;
		}		
		Query q = em.createQuery(queryString);
		q.executeUpdate();
	}
}
