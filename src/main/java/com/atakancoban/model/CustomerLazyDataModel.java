package com.atakancoban.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.atakancoban.orm.ZzAtakanPerson;
import com.atakancoban.service.CustomerService;

public class CustomerLazyDataModel extends LazyDataModel<ZzAtakanPerson> {

	private static final long serialVersionUID = 1928300515443607970L;
	private CustomerService customerService;

	public CustomerLazyDataModel() {
	}

	public CustomerLazyDataModel(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Object getRowKey(ZzAtakanPerson object) {
		return object.getPersonId();
	}

	@Override
	public ZzAtakanPerson getRowData(String rowKey) {
		try {

			@SuppressWarnings("unchecked")
			List<ZzAtakanPerson> list = (List<ZzAtakanPerson>) getWrappedData();
			for (ZzAtakanPerson obj : list) {
				if (obj.getPersonId().toString().equals(rowKey))
					return obj;

			}

		} catch (Exception e) {

			System.out.println("getRowData method error" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
	
	
	@Override
	public List<ZzAtakanPerson> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<ZzAtakanPerson> list = null;
		try {

			int rowCount = customerService.getCountOfCustomer(filters);
			this.setRowCount(rowCount);
			
			list = customerService.getDataOfCustomer(first, pageSize, sortField, sortOrder, filters);
		} catch (Exception e) {
			this.setRowCount(0);
			System.out.println("customarLazyModel LOAD method error" +e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
