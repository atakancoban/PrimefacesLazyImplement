package com.atakancoban.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.atakancoban.dao.base.GenericDaoImpl;
import com.atakancoban.orm.ZzAtakanPerson;

@Repository("personDao")
public class PersonDaoImpl extends GenericDaoImpl<ZzAtakanPerson> implements PersonDao, Serializable {

	private static final long serialVersionUID = 1036158609629705297L;

	public PersonDaoImpl() {
		super(ZzAtakanPerson.class);
	}

}
