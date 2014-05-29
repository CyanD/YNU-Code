package com.action.addressbook;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.AddressbookService;
import com.vo.bean.AddressbookDepartmentVO;
import com.ynu.zjx.SuperAction;
@Component("addressbookDepartmentAction")
@Scope("prototype")
public class AddressbookDepartmentAction extends SuperAction implements ModelDriven<AddressbookDepartmentVO>{
	private AddressbookDepartmentVO vo = new AddressbookDepartmentVO();
	private AddressbookService addressbookService;
	public AddressbookService getAddressbookService() {
		return addressbookService;
	}

	@Override
	public AddressbookDepartmentVO getModel() {
		return this.vo;
	}
	@Resource
	public void setAddressbookService(AddressbookService addressbookService) {
		this.addressbookService = addressbookService;
	}

	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.addressbookService.findAddressbookDepartmentTreeGrid(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	
	public String add() throws Exception {
		print(vo);
		try {
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.addressbookService.saveAddressbookDepartmentTreeGrid(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("保存成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
		
	}
	
	public String remove() throws Exception {
		try {
			this.jsonString =this.addressbookService.deleteAddressbookDepartmentTreeGrid(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！该院系已被使用！不能删除！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.addressbookService.updateAddressbookDepartmentTreeGrid(this.vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("修改成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,false);
		}
		return null;
	}
	/*	public String showTree() throws Exception {//暂时没用
	this.jsonString = this.addressbookService.findAddressbookDepartmentTree(vo);
	outputJson(this.jsonString,true);
	return null;
	}*/
}
