package com.sys.services.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.core.paging.Page;
import com.jpa.BaseDAO;
import com.sys.model.Product;
import com.sys.model.Viewproduct;
import com.sys.services.IProductService;
@Service("productService")
public class ProductService extends BaseDAO<Product> implements IProductService {

	public ProductService() {
	}
	
	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Override
	public void removeById(long id) {
		// TODO Auto-generated method stub
		removeByClause("Product", " where id=" + id);
	}

	/**
	 * 通过条件从句删除数据
	 * 
	 * @param objName
	 * @param clause
	 */
	@SuppressWarnings("unchecked")	
	private void removeByClause(String objName, String clause) {

		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("delete from Product");
			
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
	public List<Product> findByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findByClause(clause);
	}

	@Override
	public Product findOneByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findOneByClause(clause);
	}

	@Override
	public List<Product> findPageByClasuse(String clause, Page page) {
		// TODO Auto-generated method stub
		int _allRow = super.getCount(clause);
		page.init(_allRow, page.getCurrentPage(),
				page.getPageSize() > 0 ? page.getPageSize() : Page.PAGE_SIZE);
		return super.findPageByClasuse(page, clause);
	}

	@Override
	public int getCount(String clause) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addOrUpdateProduct(Product obj) {
		// TODO Auto-generated method stub
		boolean newFlag = false;

		if (obj.getProductid() != null && obj.getProductid() > 0) {
			super.update(obj);
		} else {
			newFlag = true;
			//EntityTransaction trans = null;
			try {
				//trans = em.getTransaction();
				//trans.begin();
				 obj.setProductid(null);
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
	public List<Viewproduct> findViewproductByClasuse(String clause) {
		// TODO Auto-generated method stub
		return super.findByClause(Viewproduct.class, clause);
	}
}
