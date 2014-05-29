package com.action.publicservice;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ynu.zjx.SuperAction;
import com.model.publicservice.YnuStory;
import com.opensymphony.xwork2.ModelDriven;
import com.service.YnuStoryService;
import com.util.MyUploadTool;
import com.util.Sys;
import com.vo.bean.YnuStoryVO;

@Component("ynuStoryAction")
@Scope("prototype")
public class YnuStoryAction extends SuperAction implements ModelDriven<YnuStoryVO>{
	private YnuStoryVO vo = new YnuStoryVO();
	private YnuStoryService ynuStoryService;
	private YnuStory modelLazy;
	
	public YnuStoryService getYnuStoryService() {
		return ynuStoryService;
	}
	@Resource
	public void setYnuStoryService(YnuStoryService ynuStoryService) {
		this.ynuStoryService = ynuStoryService;
	}
	public YnuStory getModelLazy() {
		return modelLazy;
	}
	public void setModelLazy(YnuStory modelLazy) {
		this.modelLazy = modelLazy;
	}

	@Override
	public YnuStoryVO getModel() {
		return this.vo;
	}


	public String show() throws Exception {
		print(vo);
		try {
			this.jsonString = this.ynuStoryService.findAllYnuStory(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputJson(this.jsonString,true);
		return null;
	}
	public String add() throws Exception {
		print(vo);
		try {
			vo.setContent( (vo.getContent()).replaceAll("\"", "'").replaceAll("src='/umobile", "src='/ynumobile") );
			//vo.setContent( (vo.getContent()).replaceAll("\"", "'"));
			vo.setLoginUser(this.loginUser);//后用session
			this.jsonString=this.ynuStoryService.saveYnuStory(vo);
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
			this.jsonString =this.ynuStoryService.deleteYnuStory(vo);
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
			vo.setContent( (vo.getContent()).replaceAll("\"", "'").replaceAll("src='/umobile", "src='/ynumobile") );
			this.jsonString = this.ynuStoryService.updateYnuStory(vo);
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
	
	public String showDetail() throws Exception {
        
		modelLazy=this.ynuStoryService.findYnuStoryById(vo);
		return "showDetail";
		//return toJsonString(modelLazy);
	}
	public String addHead() throws Exception {
		try{
			String name = "";
			String path="";
			String imgType="";
			for (int i = 0; i < vo.getFiledata().size(); i++) {
				name = vo.getFiledataFileName().get(i);
				boolean isAllow =false;
				if (name.toLowerCase().endsWith(".jpg")||name.toLowerCase().endsWith(".jpeg")) {
					imgType = "JPEG";
					isAllow =true;
				}else if (name.toLowerCase().endsWith(".png")) {
					imgType = "PNG";
					isAllow =true;
				}else if(name.toLowerCase().endsWith(".gif")){
					imgType = "GIF";
					isAllow =true;
				}else if(name.toLowerCase().endsWith(".bmp")){
					imgType = "BMP";
					isAllow =true;
				}
				if(isAllow){
					path = Sys.YNUSTORYHEADBASEPATH+System.currentTimeMillis()+".png";
					//MyUploadTool.saveMiniImage(vo.getFiledata().get(i), MyUploadTool.toRealPath(path), 64, 64, imgType);
					
					MyUploadTool.saveYnuStoryImage(vo.getFiledata().get(i), MyUploadTool.toRealPath(path), imgType);
					
					this.vo.setHeadpath(path);
					this.jsonString=this.ynuStoryService.saveYnuStoryHeadBack(vo);
					if(this.jsonString==null){
						resultJson.setSuccess(true);
						resultJson.setMsg(path);
					}
				}else{
					resultJson.setSuccess(false);
					resultJson.setMsg("图片不是规定格式，只能上传.jpg.png.gif.bmp格式的图片。");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			this.outputJson(this.jsonString,false);
		}
		return null;	
	}
	public String search() throws Exception {
		try {
			this.jsonString =this.ynuStoryService.findSearchYnuStoryByName(vo);
			resultJson.setSuccess(true);
			resultJson.setMsg("success！");
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
	
}

