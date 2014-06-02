package com.sys.actions;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actions.BaseAction;
import com.core.paging.Page;
import com.sys.model.ProductType;
import com.sys.services.IProductTypeService;
@Controller
public class ProductTypeAction  extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3599578935727280721L;
	private ProductType producttype;
	private List<ProductType> tProductTypes;
	private Integer page = 0;
	private Integer total = 0;
	private Integer rows = 0;
	private Integer records = 0;
	private String sord;
	private String sidx;
	private String search;
	private String datasStr;
	@Resource(name = "producttypeService")
	private IProductTypeService producttypeService = null;
	private Page mypage = null;
	private String userIdStrs = null;
	
	

	public ProductTypeAction() {
		
		setProducttype(new ProductType());
		setMypage(new Page());
	}
	
	@RequestMapping("/ProductTypelist.do")
	public void ProductTypelist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sqlClause="";
			//sqlClause=" where id=1";
			tProductTypes = producttypeService.findByClasuse(sqlClause);
			
		//request.setAttribute("roles", roles);
		super.outJsonArray(tProductTypes, response);
	}

	public ProductType getProducttype() {
		return producttype;
	}

	public void setProducttype(ProductType producttype) {
		this.producttype = producttype;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getDatasStr() {
		return datasStr;
	}

	public void setDatasStr(String datasStr) {
		this.datasStr = datasStr;
	}

	public Page getMypage() {
		return mypage;
	}

	public void setMypage(Page mypage) {
		this.mypage = mypage;
	}

	public String getUserIdStrs() {
		return userIdStrs;
	}

	public void setUserIdStrs(String userIdStrs) {
		this.userIdStrs = userIdStrs;
	}

}
