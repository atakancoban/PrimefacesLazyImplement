package com.atakancoban.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import com.atakancoban.dao.base.GenericDaoImpl;
import com.atakancoban.orm.ZzAtakanPerson;

@Repository("customerDao")
public class CustomerDaoImpl extends GenericDaoImpl<ZzAtakanPerson> implements CustomerDao, Serializable {

	private static final long serialVersionUID = -2631004079853962650L;

	public CustomerDaoImpl() {
		super(ZzAtakanPerson.class);
	}

	@Override
	public List<ZzAtakanPerson> getDataOfCustomer(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) throws Exception {

		List<ZzAtakanPerson> customerList = null;
		try {

			Session session = getCurrentSession();
			//deprecated
			// Criteria crit = session.createCriteria(ZzAtakanPerson.class);

			// new version
			// builder oluştur
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			// tablo ne dönecek entity.class liste veya tek satır dönebilir .
			CriteriaQuery<ZzAtakanPerson> critQuery = criteriaBuilder.createQuery(ZzAtakanPerson.class);
			// select*from entity.class düşün root sorgu
			Root<ZzAtakanPerson> root = critQuery.from(ZzAtakanPerson.class);
			// select root yani select * from entity.class şuan aşağıda
			// where,orderby eklendi
			critQuery.select(root);

			// Where
			if (!filters.isEmpty()) {
				Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();

					critQuery.where(criteriaBuilder.like(criteriaBuilder.upper(root.get(entry.getKey())),
							"%" + entry.getValue().toString().toUpperCase(new Locale("tr", "TR")) + "%"));

				}
			}

			// Order By

			if (sortField != null && !sortField.isEmpty()) {
				if (sortOrder.equals(SortOrder.ASCENDING)) {
					// crit = crit.addOrder(Order.asc(sortField));
					// order by - yukarıda select,where yapılmıştır şimdi order
					// eklendi yine criteriabuilder.
					critQuery.orderBy(criteriaBuilder.asc(root.get(sortField)));
				} else {
					// crit = crit.addOrder(Order.desc(sortField));

					critQuery.orderBy(criteriaBuilder.desc(root.get(sortField)));
				}
			}

			// query first , max parameterleri ve getResultList() eski hali
			// list()di
			customerList = session.createQuery(critQuery).setFirstResult(first).setMaxResults(first + pageSize)
					.getResultList();

		} catch (Exception e) {
			throw new Exception("getDataOfCustomer() method error " + e.getMessage(), e);
		}
		return customerList;
	}

	@Override
	public int getCountOfCustomer(Map<String, Object> filters) throws Exception {
		int customerTotalCount = 0;
		try {

			Session session = getCurrentSession();

			//deprecated
			// session.createCriteria(ZzAtakanPerson.class).setProjection(Projections.rowCount());

			// Builder oluştur
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			// query oluştur ne dönüyor entitiy.class
			CriteriaQuery<Long> critQuery = criteriaBuilder.createQuery(Long.class);
			// select * from (entity.class) gibi düşün root class
			Root<ZzAtakanPerson> root = critQuery.from(ZzAtakanPerson.class);
			// select cümlesi - builder ile count al , tüm data için root
			// yazılır.
			critQuery.select(criteriaBuilder.count(root));

			// Where
			if (!filters.isEmpty()) {
				Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();

					critQuery.where(criteriaBuilder.like(criteriaBuilder.upper(root.get(entry.getKey())),
							"%" + entry.getValue().toString().toUpperCase(new Locale("tr", "TR")) + "%"));

				}
			}

			Query query = session.createQuery(critQuery);
			// uniqueResult değil getSingleResult() gelen tek veriyi al obje de
			// olabilir
			if (query.getSingleResult() != null) {
				customerTotalCount = ((Long) query.getSingleResult()).intValue();

			} else
				customerTotalCount = 0;
		} catch (Exception e) {
			throw new Exception("getCountOfCustomer() method error " + e.getMessage(), e);
		}

		return customerTotalCount;
	}

}
