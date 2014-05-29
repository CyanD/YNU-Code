package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.YnuStoryDAO;
import com.model.publicservice.YnuStory;

@Component("ynuStoryDAO")
public class YnuStoryDAOImpl extends TopDAO implements YnuStoryDAO{
	
	@Override
	public List<YnuStory> findAllYnuStory(int i, int rows, String sort, String order){
		return this.getSession().createQuery("from YnuStory order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();

	}
	
	@Override
	public long findTotal() {
		return (Long)this.getSession().createQuery("select count(*) from YnuStory").uniqueResult();
	}

	 @Override
     public List<YnuStory> findSearchYnuStory(int i, int rows, String sort,String order,String fieldName,String fieldValue){
    	 return (List<YnuStory>)this.getSession().createQuery("from YnuStory where instr("+fieldName+",'"+fieldValue+"')>0 order by "+sort+" "+order).setFirstResult(i).setMaxResults(rows).list();
		 	
     }
	 @Override
		public long searchTotal(String fieldName,String fieldValue){
			return (Long)this.getSession().createQuery("select count(*) from YnuStory where instr("+fieldName+",'"+fieldValue+"')>0").uniqueResult();
		}
		
	@Override
	public void saveYnuStory(YnuStory activity) {
		this.getHibernateTemplate().save(activity);
	}
	
	@Override
	public YnuStory findYnuStoryById(Long id) {
		return (YnuStory) this.getSession().get(YnuStory.class, id);
	}

	@Override
	public void deleteYnuStory(YnuStory activity) {
		this.getHibernateTemplate().delete(activity);
	}

	@Override
	public void mergeYnuStory(YnuStory ynustory) {
		
		//this.getHibernateTemplate().merge(ynustory);
		
		this.getHibernateTemplate().update(ynustory);
	}

}
