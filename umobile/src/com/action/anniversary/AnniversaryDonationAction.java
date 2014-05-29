package com.action.anniversary;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.service.AnniversaryService;
import com.vo.bean.AnniversaryDonationVO;
import com.ynu.zjx.SuperAction;
@Component("anniversaryDonationAction")
@Scope("prototype")
public class AnniversaryDonationAction extends SuperAction implements ModelDriven<AnniversaryDonationVO>{
	private AnniversaryDonationVO vo = new AnniversaryDonationVO();
	private AnniversaryService anniversaryService;
	public AnniversaryService getAnniversaryService() {
		return anniversaryService;
	}

	@Override
	public AnniversaryDonationVO getModel() {
		return this.vo;
	}
	@Resource
	public void setAnniversaryService(AnniversaryService anniversaryService) {
		this.anniversaryService = anniversaryService;
	}

	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.anniversaryService.findAnniversaryDonations(vo);
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
			this.jsonString=this.anniversaryService.saveAnniversaryDonation(vo);
			print(jsonString);
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
			this.jsonString =this.anniversaryService.deleteAnniversaryDonation(vo);
			if(this.jsonString==null){
				resultJson.setSuccess(true);
				resultJson.setMsg("删除成功！");
				this.jsonString = toJsonString(resultJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请刷新后重试！");
			this.jsonString = toJsonString(resultJson);
		}finally{
			this.outputJson(this.jsonString,true);
		}
		return null;
	}
	
	public String edit() throws Exception {
		vo.setLoginUser(loginUser);
		try {
			this.jsonString = this.anniversaryService.updateAnniversaryDonation(this.vo);
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
}
