package com.atakancoban.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.springframework.web.context.annotation.SessionScope;

import com.atakancoban.model.CustomerLazyDataModel;
import com.atakancoban.orm.ZzAtakanPerson;
import com.atakancoban.service.CustomerService;

@ManagedBean(name = "customerBean")
@SessionScope
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = -7399564020028492039L;

	private LazyDataModel<ZzAtakanPerson> lazyModelCustomer;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;

	
	@PostConstruct
	public void init() {
		 lazyModelCustomer = new CustomerLazyDataModel(customerService);
	}
	
	
	
	
	public LazyDataModel<ZzAtakanPerson> getLazyModelCustomer() {
		return lazyModelCustomer;
	}

	public void setLazyModelCustomer(LazyDataModel<ZzAtakanPerson> lazyModelCustomer) {
		this.lazyModelCustomer = lazyModelCustomer;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
