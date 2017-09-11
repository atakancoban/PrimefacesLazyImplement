package com.atakancoban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atakancoban.dao.PersonDao;
import com.atakancoban.orm.ZzAtakanPerson;

@Service("personService")
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<ZzAtakanPerson> getPersonList() throws Exception {
		return personDao.getOrderedAsc("personId");
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void deletePerson(ZzAtakanPerson entity) throws Exception {
		personDao.delete(entity);
		personDao.flushAndClear();
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void updatePerson(ZzAtakanPerson entity) throws Exception {
		personDao.update(entity);
		personDao.flushAndClear();

	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void addPerson(ZzAtakanPerson entity) throws Exception {

		personDao.save(entity);
		personDao.flushAndClear();
	}

}
