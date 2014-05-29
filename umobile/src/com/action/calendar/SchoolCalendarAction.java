package com.action.calendar;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.model.calendar.SchoolCalendar;
import com.model.calendar.SchoolCalendarLazy;
import com.opensymphony.xwork2.ModelDriven;

import com.service.CalendarService;
import com.service.VersionsService;
import com.vo.bean.SchoolCalendarVO;
import com.ynu.zjx.SuperAction;
import com.ynu.zjx.SuperJson;
@Component("schoolCalendarAction")
@Scope("prototype")
public class SchoolCalendarAction extends SuperAction implements ModelDriven<SchoolCalendarVO>{
	private SchoolCalendarVO schoolCalendarVO = new SchoolCalendarVO();
	private CalendarService calendarService;
	private VersionsService versionsService;
	private SchoolCalendar schoolCalendar = new SchoolCalendar();
	private SchoolCalendarLazy schoolCalendarLazy = new SchoolCalendarLazy();
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
	public SchoolCalendarVO getModel() {
		return this.schoolCalendarVO;
	}

	public SchoolCalendarVO getSchoolCalendarVO() {
		return schoolCalendarVO;
	}
	public void setSchoolCalendarVO(SchoolCalendarVO schoolCalendarVO) {
		this.schoolCalendarVO = schoolCalendarVO;
	}
	@Resource
	public void setCalendarService(CalendarService  calendarService) {
		this.calendarService = calendarService;
	}

	@SuppressWarnings("unchecked")
	public String show() throws Exception {
        List<SchoolCalendar> rows = (List<SchoolCalendar>)this.calendarService.findschoolCalendarByPage(schoolCalendarVO.getCid(),schoolCalendarVO.getRows()*(schoolCalendarVO.getPage()-1), schoolCalendarVO.getRows(),schoolCalendarVO.getSort(),schoolCalendarVO.getOrder());   
    	long total = this.calendarService.findschoolCalendarTotal(schoolCalendarVO.getCid()); 
        SuperJson<SchoolCalendar> superJson = new SuperJson<SchoolCalendar>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		this.jsonString = toJsonString(superJson);
		super.outputJson(jsonString, true);
		return null;
	}
	public String save() throws Exception {
		System.out.println(schoolCalendarVO.getCid());
		this.schoolCalendarLazy.setCid(schoolCalendarVO.getCid());
		this.schoolCalendarLazy.setActtime(schoolCalendarVO.getActtime());
		this.schoolCalendarLazy.setPublisher(loginUser.getName());
		this.calendarService.saveschoolCalendarLazy(this.schoolCalendarLazy);
		this.versionsService.updateversion();
		resultJson.setSuccess(true);
		resultJson.setMsg("保存成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}
	public String delete() throws Exception {
		try {
			this.calendarService.deleteschoolCalendarByIds(this.schoolCalendarVO.getIds());
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
		this.schoolCalendarLazy.setId(this.schoolCalendarVO.getId());
		this.schoolCalendarLazy.setCid(this.schoolCalendarVO.getCid());
		this.schoolCalendarLazy.setActtime(this.schoolCalendarVO.getActtime());
		this.schoolCalendarLazy.setPublisher(loginUser.getName());
		try {
			this.calendarService.updateschoolCalendarLazy(this.schoolCalendarLazy);
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
		resultJson.setSuccess(true);
		resultJson.setMsg("修改成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}

}
