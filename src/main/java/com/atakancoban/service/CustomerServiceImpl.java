package com.atakancoban.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atakancoban.dao.CustomerDao;
import com.atakancoban.orm.ZzAtakanPerson;

@Service("customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<ZzAtakanPerson> getDataOfCustomer(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) throws Exception {
		return customerDao.getDataOfCustomer(first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public int getCountOfCustomer(Map<String, Object> filters) throws Exception {
		return customerDao.getCountOfCustomer(filters);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	
	
	
}
