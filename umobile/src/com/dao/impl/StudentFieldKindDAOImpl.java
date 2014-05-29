package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.StudentFieldKindDAO;
import com.model.publicservice.StudentFieldKind;
import com.model.publicservice.StudentFieldKindTree;

@Component("studentFieldKindDAO")
public class StudentFieldKindDAOImpl extends TopDAO implements StudentFieldKindDAO{
	
	@Override
	public List<StudentFieldKind> findAllStudentFieldKind(){
		return this.getSession().createQuery("from StudentFieldKind").list();

	}

	@Override
	public List<StudentFieldKindTree> findAllStudentFieldKindTree(){
		return this.getSession().createQuery("from StudentFieldKindTree").list();

	}
	
	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from StudentFieldKind").uniqueResult();
	}

	@Override
	public void saveStudentFieldKind(StudentFieldKind activity) {
		this.getHibernateTemplate().save(activity);
	}

	@Override
	public StudentFieldKind findStudentFieldKindById(Long id) {
		return (StudentFieldKind) this.getSession().get(StudentFieldKind.class, id);
	}

	@Override
	public void deleteStudentFieldKind(StudentFieldKind activity) {
		this.getHibernateTemplate().delete(activity);
	}


	@Override
	public void mergeStudentFieldKind(StudentFieldKind activity) {
		this.getHibernateTemplate().merge(activity);
	}
	
	@Override
	public void updateStudentFieldKind(StudentFieldKind activity) {
		this.getHibernateTemplate().update(activity);
	}

}
