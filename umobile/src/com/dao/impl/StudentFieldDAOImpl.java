package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import com.dao.StudentFieldDAO;
import com.model.publicservice.StudentField;
import com.model.publicservice.StudentFieldLazy;
import com.model.publicservice.StudentFieldKindTree;

@Component("studentFieldDAO")
public class StudentFieldDAOImpl extends TopDAO implements StudentFieldDAO{
	
	@Override
	public List<StudentField> findAllStudentField(int i, int rows, String sort, String order){
		return this.getSession().createQuery("from StudentField order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public List<StudentFieldLazy> searchAllStudentField(int i, int rows, String sort, String order,Long key){
		return this.getSession().createQuery("from StudentFieldLazy where kindId = "+key+" order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}
	@Override
	public StudentFieldKindTree findKindTree(Long id){
		return (StudentFieldKindTree)this.getSession().createQuery("from StudentFieldKindTree where id = "+id).uniqueResult();
	}
	@Override
	public long searchTotal(Long key){
		return (Long)this.getSession().createQuery("select count(*) from StudentField where kindId = "+key).uniqueResult();
	}
	
	
	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from StudentField").uniqueResult();
	}

	@Override
	public void saveStudentField(StudentFieldLazy activity) {
		this.getHibernateTemplate().save(activity);
	}
	
	@Override
	public StudentField findStudentFieldById(Long id) {
		return (StudentField) this.getSession().get(StudentField.class, id);
	}

	@Override
	public void deleteStudentField(StudentField activity) {
		this.getHibernateTemplate().delete(activity);
	}


	@Override
	public void mergeStudentField(StudentFieldLazy activity) {
		this.getHibernateTemplate().merge(activity);
	}

	@Override
	public StudentFieldLazy findStudentFieldLazyById(Long id){
		return (StudentFieldLazy) this.getSession().get(StudentFieldLazy.class, id);
	}
}
