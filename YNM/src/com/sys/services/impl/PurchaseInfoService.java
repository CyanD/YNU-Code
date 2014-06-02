package com.sys.services.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.core.paging.Page;
import com.jpa.BaseDAO;
import com.sys.model.PurchaseInfo;
import com.sys.model.Viewpurchase;
import com.sys.services.IPurchaseInfoService;
@Service("purchaseinfoService")
public class PurchaseInfoService extends BaseDAO<PurchaseInfo> implements IPurchaseInfoService {

	public PurchaseInfoService(){
		
	}
	@Override
	public void addOrUpdateInfo(PurchaseInfo obj) {
		// TODO Auto-generated method stub
		boolean newFlag = false;

		if (obj.getPinfoid() != null && obj.getPinfoid() > 0) {
			super.update(obj);
		} else {
			newFlag = true;
			//EntityTransaction trans = null;
			try {
				//trans = em.getTransaction();
				//trans.begin();
				 obj.setPinfoid(null);
				
				super.save(obj);
//				addDefaultModuleFunToDB(obj);
				//trans.commit();
			} catch (Exception ex) {
				log.error("addOrUpdateCompany>>>>>"+ex.getMessage());
				//if (trans != null) {
				//	trans.rollback();
				//}
			}
	}
	}

	@Override
	public List<PurchaseInfo> findByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findByClause(clause);
	}

	@Override
	public PurchaseInfo findById(long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}
	
	
	@Override
	public int getCount(String clause){
		return super.getCount(Viewpurchase.class, clause);
	}

	@Override
	public PurchaseInfo findOneByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findOneByClause(clause);
	}

	@Override
	public List<Viewpurchase> findPageByClasuse(String clause, Page page) {
		// TODO Auto-generated method stub
		
		int _allRow = super.getCount(clause);
		page.init(_allRow, page.getCurrentPage(),
				page.getPageSize() > 0 ? page.getPageSize() : Page.PAGE_SIZE);
		return super.findPageByClasuse(Viewpurchase.class,page, clause);
	}

	@Override
	public void removeById(long id) {
		// TODO Auto-generated method stub
		removeByClause("PurchaseInfo", " where id=" + id);
		
	}
	@SuppressWarnings("unchecked")
	private void removeByClause(String objName, String clause) {
		// TODO Auto-generated method stub
try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("delete from PurchaseInfo");
			
			if (clause != null && !"".equals(clause)) {
				sb.append(clause);
			}
			Query query1 = em.createQuery(sb.toString());// session.createQuery(sb.toString());
			query1.executeUpdate();
			
		} catch (RuntimeException re) {

			throw re;
		}
	}
	@Override
	public List<Viewpurchase> findViewpurchaseByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findByClause(Viewpurchase.class, clause);
	}

}

