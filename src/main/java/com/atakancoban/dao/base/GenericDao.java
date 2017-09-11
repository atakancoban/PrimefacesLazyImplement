package com.atakancoban.dao.base;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	long countAll();

	List<T> getAll();

	Serializable save(T t);

	void delete(T t);

	void update(T t);

	void saveOrUpdate(T t);

	void saveOrUpdate(List<T> list);

	void flushAndClear();
	void applyChanges();

	List<T> getOrderedAsc(String propertyName);


	List<T> getOrderedDesc(String propertyName);

}
