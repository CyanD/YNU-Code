package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import com.dao.TeacherHomeDAO;
import com.model.publicservice.TeacherHome;
import com.model.publicservice.TeacherHomeKind;
import com.model.publicservice.TeacherHomeLazy;
import com.model.publicservice.TeacherHomeKindTree;

@Component("teacherHomeDAO")
public class TeacherHomeDAOImpl extends TopDAO implements TeacherHomeDAO{
	
	@Override
	public List<TeacherHome> findAllTeacherHome (int i, int rows, String sort, String order){
		return this.getSession().createQuery("from TeacherHome order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}

	@Override
	public List<TeacherHomeLazy> searchAllTeacherHome(int i, int rows, String sort, String order,Long key){
		return this.getSession().createQuery("from TeacherHomeLazy where kindId = "+key+" order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
	}
	@Override
	public TeacherHomeKindTree findKindTree(Long id){
		return (TeacherHomeKindTree)this.getSession().createQuery("from TeacherHomeKindTree where id = "+id).uniqueResult();
	}
	@Override
	public long searchTotal(Long key){
		return (Long)this.getSession().createQuery("select count(*) from TeacherHome where kindId = "+key).uniqueResult();
	}
	
	
	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from TeacherHome").uniqueResult();
	}

	@Override
	public void saveTeacherHome(TeacherHomeLazy activity) {
		this.getHibernateTemplate().save(activity);
	}
    
	@Override
	public TeacherHome findTeacherHomeById(Long id) {
		return (TeacherHome) this.getSession().get(TeacherHome.class, id);
	}
	
	
	@Override
	public void deleteTeacherHome(TeacherHome activity) {
		this.getHibernateTemplate().delete(activity);
	}


	@Override
	public void mergeTeacherHome(TeacherHomeLazy activity) {
		this.getHibernateTemplate().merge(activity);
	}

	@Override
	public TeacherHomeLazy findTeacherHomeLazyById(Long id){
		return (TeacherHomeLazy) this.getSession().get(TeacherHomeLazy.class, id);
	}
}
