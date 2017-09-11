package com.atakancoban.service;

import java.util.List;

import com.atakancoban.orm.ZzAtakanPerson;

public interface PersonService {
	
	 List<ZzAtakanPerson> getPersonList() throws Exception;
	
	 void deletePerson(ZzAtakanPerson entity) throws Exception;
	
	 void updatePerson(ZzAtakanPerson entity) throws Exception;
	
	 void addPerson(ZzAtakanPerson entity) throws Exception;
	
	
}
