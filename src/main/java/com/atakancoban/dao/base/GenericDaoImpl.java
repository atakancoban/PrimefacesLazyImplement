package com.atakancoban.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T extends Serializable> implements Serializable, GenericDao<T> {

	private static final long serialVersionUID = 1L;

	private Class<T> persistentClass;
	private SessionFactory sessionFactory;

	public GenericDaoImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Override
	public long countAll() {
		return getRowCount(getAllRecordsCriteria());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		List<T> list = getAllRecordsCriteria().list();
		System.out.println(persistentClass.getSimpleName() + " getAll() success");
		return list;
	}

	@Override
	public Serializable save(T t) {
		Serializable serializable = getCurrentSession().save(t);

		System.out.println(persistentClass.getSimpleName() + " save() success");
		return serializable;
	}

	@Override
	public void update(T t) {
		getCurrentSession().update(t);

		System.out.println(persistentClass.getSimpleName() + " update() success");
	}

	@Override
	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
		System.out.println(persistentClass.getSimpleName() + " saveOrUpdate() success");
	}

	@Override
	public void saveOrUpdate(List<T> list) {
		getCurrentSession().saveOrUpdate(list);
		System.out.println(persistentClass.getSimpleName() + " saveOrUpdate(list) success");
	}

	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
		System.out.println(persistentClass.getSimpleName() + " delete() success");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedAsc(String propertyName) {
		return getAllRecordsCriteria().addOrder(Order.asc(propertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedDesc(String propertyName) {
		return getAllRecordsCriteria().addOrder(Order.desc(propertyName)).list();
	}

	protected long getRowCount(Criteria criteria) {
		long rowCount = (Long) criteria.setProjection(Projections.rowCount()).list().get(0);
		return rowCount;
	}

	protected Criteria getAllRecordsCriteria() {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		return criteria;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected void setPersistentClass(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public void flushAndClear() {
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

	@Override
	public void applyChanges() {
		getCurrentSession().flush();
	}

}
