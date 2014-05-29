package com.action;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.service.PublicService;
import com.ynu.zjx.SuperAction;
@Component("publicAction")
@Scope("prototype")
public class PublicAction extends SuperAction{
	private PublicService publicService;
	public PublicService getPublicService() {
		return publicService;
	}
	@Resource
	public void setPublicService(PublicService publicService) {
		this.publicService = publicService;
	}
	public String showSysDeptTree() throws Exception {
        try {
			this.outputJson(this.publicService.findSysDeptTreeGet(),true);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		return null;
	}
	public String showNewsAlbumCategoryTree() throws Exception {
		try {
			this.outputJson(this.publicService.findNewsAlbumCategoryTreeGet(),true);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		return null;
	}
	public String showNewsNewsCategoryTree() throws Exception {
		try {
			jsonString=this.publicService.findNewsNewsCategoryTreeGet();
			this.outputJson(jsonString,true);
			print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		return null;
	}
	public String showRoleCombogrid() throws Exception {
        try {
        	this.jsonString=this.publicService.findRoleCombogrid();
			this.outputJson(this.jsonString,true);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showAdressbookDeptTree() throws Exception {
		try {
			this.jsonString=this.publicService.findAddressbookDeptTree();
			this.outputJson(this.jsonString,true);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showAdressbookDepartmentTree() throws Exception {
		try {
			this.jsonString=this.publicService.findAddressbookDepartmentTree();
			this.outputJson(this.jsonString,true);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showCourseCategoryTree() throws Exception {
		try {
			this.jsonString=this.publicService.findCourseCategoryTree();
			this.outputJson(this.jsonString,true);
			print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showNoticeCategoryTree() throws Exception {
		try {
			this.jsonString=this.publicService.findNoticeCategoryTree();
			this.outputJson(this.jsonString,true);
			print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showTeacherHomeKindTree() throws Exception {
		try {
			this.jsonString=this.publicService.findTeacherHomeKindTree();
			this.outputJson(this.jsonString,true);
			print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
	public String showStudentFieldKindTree() throws Exception {
		try {
			this.jsonString=this.publicService.findStudentFieldKindTree();
			this.outputJson(this.jsonString,true);
			print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return null;
	}
}
