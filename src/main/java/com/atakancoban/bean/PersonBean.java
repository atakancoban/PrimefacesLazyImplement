package com.atakancoban.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.event.RowEditEvent;
import org.springframework.web.context.annotation.SessionScope;

import com.atakancoban.orm.ZzAtakanPerson;
import com.atakancoban.service.PersonService;

@ManagedBean(name = "personBean")
@SessionScope
public class PersonBean implements Serializable {

	private static final long serialVersionUID = -8162090623891464980L;

	private List<ZzAtakanPerson> listPersonEntity;

	@ManagedProperty(value = "#{personService}")
	private PersonService personService;

	private String firstname, lastname, job, city, adress;

	@PostConstruct
	public void init() {
		try {

			loadTable();

		} catch (Exception e) {
			System.out.println("init() Error. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void onDelete(ZzAtakanPerson entity) {
		try {

			personService.deletePerson(entity);
			System.out.println(entity.getPersonId().longValue());
			loadTable();

		} catch (Exception e) {
			System.out.println("onDelete() Error. " + e.getMessage());
			e.printStackTrace();

		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {

			ZzAtakanPerson entity = (ZzAtakanPerson) event.getObject();
			personService.updatePerson(entity);
			loadTable();
		} catch (Exception e) {
			System.out.println("onRowEdit() Error. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void onAddPerson() {
		try {

			ZzAtakanPerson entity = new ZzAtakanPerson();
			entity.setFirstname(firstname);
			entity.setLastname(lastname);
			entity.setJob(job);
			entity.setCity(city);
			entity.setAddress(adress);
			entity.setPersonId(new BigDecimal(00));

			personService.addPerson(entity);
			loadTable();

		} catch (Exception e) {
			System.out.println("onAddPerson() Error. " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void loadTable() throws Exception {

		listPersonEntity = personService.getPersonList();
	}

	// Getter - Setter

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public List<ZzAtakanPerson> getListPersonEntity() {
		return listPersonEntity;
	}

	public void setListPersonEntity(List<ZzAtakanPerson> listPersonEntity) {
		this.listPersonEntity = listPersonEntity;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
