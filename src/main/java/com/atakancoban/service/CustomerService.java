package com.atakancoban.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.atakancoban.orm.ZzAtakanPerson;

public interface CustomerService {
	List<ZzAtakanPerson> getDataOfCustomer(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) throws Exception;
	int getCountOfCustomer(Map<String, Object> filters) throws Exception;
	
	
}
