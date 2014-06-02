package com.sys.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actions.BaseAction;
import com.core.paging.Page;
import com.core.paging.Range;
import com.sys.model.ProductType;
import com.sys.model.PurchaseInfo;
import com.sys.model.Viewpurchase;
import com.sys.services.IProductTypeService;
import com.sys.services.IPurchaseInfoService;
import com.util.StringUtil;
import com.util.WebUtil;

@Controller
public class PurchaseInfoAction extends BaseAction{
	
	private static final long serialVersionUID = 3599578935727280721L;
	private PurchaseInfo purchaseinfo;
	private ProductType producttype;
	private Integer page = 0;
	private Integer total = 0;
	private Integer rows = 0;
	private Integer records = 0;
	private String sord;
	private String sidx;
	private String search;
	//private String datasStr;
	private List<PurchaseInfo> tPurchaseInfos;
	private Viewpurchase viewpurchase;
	
	@Resource(name = "purchaseinfoService")
	private IPurchaseInfoService purchaseinfoService = null;
	private Page mypage = null;
	private String userIdStrs = null;
	@Resource(name = "producttypeService")
	private IProductTypeService producttypeService = null;
	
	public PurchaseInfoAction(){
		
		purchaseinfo = new PurchaseInfo();
		mypage = new Page();
	}
	

	public String getUserIdStrs() {
		return userIdStrs;
	}

	public void setUserIdStrs(String userIdStrs) {
		this.userIdStrs = userIdStrs;
	}
	
	public Page getMypage(){
		return mypage;
	}
	public void setMypage(Page mypage) {
		// TODO Auto-generated method stub
		this.mypage = mypage;
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
	
	public Integer getTotal() {
		return total;
	}

	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public PurchaseInfo getPurchaseinfo() {
		return purchaseinfo;
	}


	public void setPurchaseinfo(PurchaseInfo purchaseinfo) {
		this.purchaseinfo = purchaseinfo;
	}
	public List<PurchaseInfo> gettPurchaseInfos() {
		return tPurchaseInfos;
	}


	public void settPurchaseInfos(List<PurchaseInfo> tPurchaseInfos) {
		this.tPurchaseInfos = tPurchaseInfos;
	}
	
	@RequestMapping("/PurchaseInfo.do")
	public void PurchaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sqlClause="";
		//sqlClause=" where id=1";
        List<Viewpurchase>  tPurchaseInfos =null;
		
		int pageN = StringUtil.toInt0(WebUtil.getPV("page", request));
		int rows = StringUtil.toInt0(WebUtil.getPV("rows", request));
		
		System.out.println(pageN +rows );
		
		Page page = new Page();
		page.setCurrentPage(pageN);
		page.setPageSize(rows);
		int total=purchaseinfoService.getCount(sqlClause);

		
		tPurchaseInfos = purchaseinfoService.findPageByClasuse(sqlClause,page);
		
		Map map=new HashMap(); 
		map.put("total",total);
		map.put("rows",tPurchaseInfos);
		
		outJson(map, response);

	//request.setAttribute("roles", roles);
	//super.outJsonArray(tPurchaseInfos, response);
}
	@RequestMapping("/editPurchaseInfo.do")
	public void editUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		this.purchaseinfo = WebUtil.request2Bean("purchaseinfo", request, PurchaseInfo.class);
		
		
		if (purchaseinfo.getPinfoid() == null || purchaseinfo.getPinfoid() < 1) {
			
		} else {
			this.purchaseinfo = this.purchaseinfoService.findOneByClasuse(" where pinfoid='" + purchaseinfo.getPinfoid() + "'");
		}
		Range<PurchaseInfo> range=new Range<PurchaseInfo>();
		range.setObj(purchaseinfo);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	@RequestMapping("/addOrUpdateInfo.do")
	public void addOrUpdateInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.purchaseinfo = WebUtil.request2Bean("purchaseinfo", request, PurchaseInfo.class);
		
		purchaseinfoService.addOrUpdateInfo(purchaseinfo);
		//SpringMVCFileUtil.saveImage("E:/WorkSpace/ynm/web","uimg",request,"","");
		
		
		Range<PurchaseInfo> range = new Range<PurchaseInfo>();
		// List<Sysuser> tobjs = new ArrayList<Sysuser>();
		// tobjs.add(user);
		range.setObj(purchaseinfo);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	@RequestMapping("/removePurchaseinfo.do")
	public void removeProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.purchaseinfo = WebUtil.request2Bean("purchaseinfo", request, PurchaseInfo.class);
		
		purchaseinfoService.removeById(purchaseinfo.getPinfoid());
		Range<PurchaseInfo> range = new Range<PurchaseInfo>();
		range.setObj(purchaseinfo);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	

	
	
	
	
	
	

	

	

	

	

	
	



	


	
	

	
	
	
	




	

}
