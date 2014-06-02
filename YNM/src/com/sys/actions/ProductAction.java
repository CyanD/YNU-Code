package com.sys.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actions.BaseAction;
import com.core.paging.Page;
import com.core.paging.Range;
import com.sys.model.Product;
import com.sys.model.ProductType;
import com.sys.model.Viewproduct;
import com.sys.services.IProductService;
import com.sys.services.IProductTypeService;
import com.util.WebUtil;
@Controller
public class ProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3599578935727280721L;
	private Product product;
	private ProductType producttype;
	private List<Product> tProducts;
	private Integer page = 0;
	private Integer total = 0;
	private Integer rows = 0;
	private Integer records = 0;
	private String sord;
	private String sidx;
	private String search;
	private String datasStr;
	
	private Viewproduct viewproduct;
	
	@Resource(name = "productService")
	private IProductService productService = null;
	private Page mypage = null;
	private String userIdStrs = null;
	@Resource(name = "producttypeService")
	private IProductTypeService producttypeService = null;

	public ProductAction() {
		
		product = new Product();
		mypage = new Page();
	}

	public String getUserIdStrs() {
		return userIdStrs;
	}

	public void setUserIdStrs(String userIdStrs) {
		this.userIdStrs = userIdStrs;
	}

	public Page getMypage() {
		return mypage;
	}

	public void setMypage(Page mypage) {
		this.mypage = mypage;
	}

	public String getDatasStr() {
		return datasStr;
	}

	public void setDatasStr(String datasStr) {
		this.datasStr = datasStr;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> gettProducts() {
		return tProducts;
	}

	public void setDatas(List<Product> tProducts) {
		this.tProducts = tProducts;
	}

	@RequestMapping("/Productlist.do")
	public void Productlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sqlClause="";
			//sqlClause=" where productid=1";
		List<Viewproduct>  tProducts =null;
		tProducts = productService.findViewproductByClasuse(sqlClause);
		
		
		
		
		
		 for(int i=0;i<tProducts.size();i++){
        	 if(tProducts.get(i).getProducttypename()==null){
        		 this.producttype=this.producttypeService.findById(tProducts.get(i).getProducttypeid());
        		 tProducts.get(i).setProducttypename(producttype.getProducttypename());
        	 }

         }
		super.outJsonArray(tProducts, response);
	}
	
	@RequestMapping("/editProduct.do")
	public void editUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		this.product = WebUtil.request2Bean("product", request, Product.class);
		if (product.getProductid() == null || product.getProductid() < 1) {
			// Timestamp tnow = DateUtil.getNowTsp();
		} else {
			this.product = this.productService.findOneByClasuse(" where productid='" + product.getProductid() + "'");
		}
		Range<Product> range=new Range<Product>();
		range.setObj(product);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	
	@RequestMapping("/addOrUpdateProduct.do")
	public void addOrUpdateProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.product = WebUtil.request2Bean("product",request, Product.class);
		
		
		Date showtime = null;
		Date deletetime = null;
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			showtime = sdf1.parse(request.getParameter("product.showtime"));
			deletetime = sdf2.parse(request.getParameter("product.deletetime"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.product.setShowtime(showtime);
		this.product.setDeletetime(deletetime);
		
		
		productService.addOrUpdateProduct(product);

		Range<Product> range = new Range<Product>();
		// List<Sysuser> tobjs = new ArrayList<Sysuser>();
		// tobjs.add(user);
		range.setObj(product);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	
	@RequestMapping("/removeProduct.do")
	public void removeProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.product = WebUtil.request2Bean("product",request, Product.class);
		
		productService.removeById(product.getProductid());
		Range<Product> range = new Range<Product>();
		range.setObj(product);
		range.setSuccess(true);
		range.setMessage("success");
		range.setTotalSize(1);
		outJson(range, response);
	}
	
}
