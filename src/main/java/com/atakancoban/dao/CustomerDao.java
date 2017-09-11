package com.atakancoban.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.atakancoban.dao.base.GenericDao;
import com.atakancoban.orm.ZzAtakanPerson;

public interface CustomerDao extends GenericDao<ZzAtakanPerson>{
	
	List<ZzAtakanPerson> getDataOfCustomer(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) throws Exception;
	int getCountOfCustomer(Map<String, Object> filters) throws Exception;
	
}
