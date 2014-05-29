package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.TeacherHomeKindDAO;
import com.model.course.CourseCategoryTreeGrid;
import com.model.publicservice.TeacherHomeKind;
import com.model.publicservice.TeacherHomeKindTree;
import com.model.sys.SysDeptTreeGridGet;

@Component("teacherHomeKindDAO")
public class TeacherHomeKindDAOImpl extends TopDAO implements TeacherHomeKindDAO{
	
	@Override
	public List<TeacherHomeKind> findAllTeacherHomeKind (){
		return this.getSession().createQuery("from TeacherHomeKind").list();

	}

	@Override
	public List<TeacherHomeKindTree> findAllTeacherHomeKindTree (){
		return this.getSession().createQuery("from TeacherHomeKindTree").list();

	}
	
	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from TeacherHomeKind").uniqueResult();
	}

	@Override
	public void saveTeacherHomeKind(TeacherHomeKind activity) {
		this.getHibernateTemplate().save(activity);
	}

	@Override
	public TeacherHomeKind findTeacherHomeKindById(Long id) {
		return (TeacherHomeKind) this.getSession().get(TeacherHomeKind.class, id);
	}

	@Override
	public void deleteTeacherHomeKind(TeacherHomeKind activity) {
		this.getHibernateTemplate().delete(activity);
	}


	@Override
	public void mergeTeacherHomeKind(TeacherHomeKind activity) {
		this.getHibernateTemplate().merge(activity);
	}
	
	@Override
	public void updateTeacherHomeKind(TeacherHomeKind activity) {
		this.getHibernateTemplate().update(activity);
	}

}
