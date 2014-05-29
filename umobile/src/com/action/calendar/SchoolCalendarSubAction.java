package com.action.calendar;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.calendar.SchoolCalendarSubLazy;
import com.opensymphony.xwork2.ModelDriven;

import com.service.CalendarService;
import com.service.VersionsService;
import com.vo.bean.SchoolCalendarSubVO;
import com.ynu.zjx.SuperAction;
import com.ynu.zjx.SuperJson;
@Component("schoolCalendarSubAction")
@Scope("prototype")
public class SchoolCalendarSubAction extends SuperAction implements ModelDriven<SchoolCalendarSubVO>{
	private SchoolCalendarSubVO schoolCalendarSubVO = new SchoolCalendarSubVO();
	private CalendarService calendarService;
	private VersionsService versionsService;
	private SchoolCalendarSubLazy schoolCalendarSubLazy = new SchoolCalendarSubLazy();
	public VersionsService getVersionsService() {
		return versionsService;
	}
	@Resource
	public void setVersionsService(VersionsService versionsService) {
		this.versionsService = versionsService;
	}
	public CalendarService getCalendarService() {
		return calendarService;
	}

	@Override
	public SchoolCalendarSubVO getModel() {
		return this.schoolCalendarSubVO;
	}

	public SchoolCalendarSubVO getSchoolCalendarSubVO() {
		return schoolCalendarSubVO;
	}
	public void setSchoolCalendarSubVO(SchoolCalendarSubVO SchoolCalendarSubVO) {
		this.schoolCalendarSubVO = SchoolCalendarSubVO;
	}
	@Resource
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	public String show() throws Exception {
        @SuppressWarnings("unchecked")
		List<SchoolCalendarSubLazy> rows = (List<SchoolCalendarSubLazy>)this.calendarService.findschoolCalendarSubByPage(schoolCalendarSubVO.getPid(),schoolCalendarSubVO.getRows()*(schoolCalendarSubVO.getPage()-1), schoolCalendarSubVO.getRows(),schoolCalendarSubVO.getSort(),schoolCalendarSubVO.getOrder()); 
        long total = this.calendarService.findschoolCalendarSubTotal(schoolCalendarSubVO.getPid()); 
        SuperJson<SchoolCalendarSubLazy> superJson = new SuperJson<SchoolCalendarSubLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		this.jsonString = toJsonString(superJson);
		outputJson(this.jsonString, true);
		
		return null;
	}
	
	public String save() throws Exception {
		this.schoolCalendarSubLazy.setPid(schoolCalendarSubVO.getPid());
		this.schoolCalendarSubLazy.setCategory(this.schoolCalendarSubVO.getCategory());
		this.schoolCalendarSubLazy.setTitle(this.schoolCalendarSubVO.getTitle());
		this.schoolCalendarSubLazy.setPublisher(loginUser.getName());//以后用session取
		try {
			this.calendarService.saveschoolCalendarSubLazy(this.schoolCalendarSubLazy);
			this.versionsService.updateversion();
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
		} catch (Exception e) {
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString, false);
		}
		
		return null;
		
	}
	public String delete() throws Exception {
		try {
			this.calendarService.deleteschoolCalendarSubByIds(this.schoolCalendarSubVO.getIds());
			this.versionsService.updateversion();
			resultJson.setSuccess(true);
			resultJson.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString,true);
		}
		return null;
	}
	public String update() throws Exception {
		this.schoolCalendarSubLazy.setId(this.schoolCalendarSubVO.getId());
		this.schoolCalendarSubLazy.setPid(schoolCalendarSubVO.getPid());
		this.schoolCalendarSubLazy.setCategory(this.schoolCalendarSubVO.getCategory());
		this.schoolCalendarSubLazy.setTitle(this.schoolCalendarSubVO.getTitle());
		this.schoolCalendarSubLazy.setPublisher(loginUser.getName());//以后用session取
		try {
			this.calendarService.updateschoolCalendarSubLazy(this.schoolCalendarSubLazy);
			this.versionsService.updateversion();
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString, false);
		}
		return null;
	}
}
