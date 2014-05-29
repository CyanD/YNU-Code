package com.action.addressbook;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.AddressbookService;
import com.vo.bean.AddressbookPublicVO;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperAction;
@Component("addressbookPublicAction")
@Scope("prototype")
public class AddressbookPublicAction extends SuperAction implements ModelDriven<AddressbookPublicVO>{
	private AddressbookPublicVO vo = new AddressbookPublicVO();
	private AddressbookService addressbookService;
	public AddressbookService getAddressbookService() {
		return addressbookService;
	}

	@Override
	public AddressbookPublicVO getModel() {
		return this.vo;
	}
	@Resource
	public void setAddressbookService(AddressbookService addressbookService) {
		this.addressbookService = addressbookService;
	}

	public String show() throws Exception {
		print(vo);
		this.jsonString = this.addressbookService.findAddressbookPublicLazys(vo);
		print(jsonString);
		outputJson(this.jsonString,true);
		return null;
	}
	public String showTree() throws Exception {//暂时没用
		this.jsonString = this.addressbookService.findAddressbookPublicTree(vo);
		outputJson(this.jsonString,true);
		return null;
	}
	public String save() throws Exception {
		try {
			vo.setPublisher(loginUser.getName());//后用session
			this.jsonString=this.addressbookService.saveAddressbookPublicLazy(vo);
			if(jsonString == null){
				ResultJson resultJson =new ResultJson();
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
	public String delete() throws Exception {
		try {
			this.jsonString =this.addressbookService.deleteAddressbookPublicLazyById(vo);
			if(jsonString == null){
				ResultJson resultJson =new ResultJson();
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	public String update() throws Exception {
		vo.setPublisher(loginUser.getName());
		try {
			this.jsonString = this.addressbookService.updateAddressbookPublicLazy(this.vo);
			if(jsonString == null){
				ResultJson resultJson =new ResultJson();
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
}
