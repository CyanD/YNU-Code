package com.action.calendar;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



import com.model.calendar.CalendarCategory;
import com.opensymphony.xwork2.ModelDriven;


import com.service.CalendarService;
import com.service.VersionsService;
import com.vo.bean.CalendarCategoryVO;
import com.ynu.zjx.SuperAction;
import com.ynu.zjx.SuperJson;
@Component("calendarCategoryAction")
@Scope("prototype")
public class CalendarCategoryAction extends SuperAction implements ModelDriven<CalendarCategoryVO>{
	private CalendarCategoryVO calendarCategoryVO = new CalendarCategoryVO();
	private CalendarService calendarService;
	private VersionsService versionsService;
	private CalendarCategory calendarCategory = new CalendarCategory();
	
	
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
	public CalendarCategoryVO getModel() {
		return this.calendarCategoryVO;
	}

	public CalendarCategoryVO getCalendarCategoryVO() {
		return calendarCategoryVO;
	}
	public void setCalendarCategoryVO(CalendarCategoryVO calendarCategoryVO) {
		this.calendarCategoryVO = calendarCategoryVO;
	}
	@Resource
	public void setCalendarService(CalendarService  calendarService) {
		this.calendarService = calendarService;
	}

	@SuppressWarnings("unchecked")
	public String show() throws Exception {
        List<CalendarCategory> rows = (List<CalendarCategory>)this.calendarService.findCalendarCategoryByPage(calendarCategoryVO.getRows()*(calendarCategoryVO.getPage()-1), calendarCategoryVO.getRows(),calendarCategoryVO.getSort(),calendarCategoryVO.getOrder());   
    	long total = this.calendarService.findCalendarCategoryTotal(); 
        SuperJson<CalendarCategory> superJson = new SuperJson<CalendarCategory>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		this.jsonString = toJsonString(superJson);
		super.outputJson(jsonString, true);
		return null;
	}
	public String save() throws Exception {
		this.calendarCategory.setTitle(this.calendarCategoryVO.getTitle());
		this.calendarCategory.setBeginDate(this.calendarCategoryVO.getBeginDate());
		this.calendarCategory.setWeeknum(this.calendarCategoryVO.getWeeknum());
		this.calendarCategory.setPublisher(loginUser.getName());
		this.calendarService.saveCalendarCategory(this.calendarCategory);
		this.versionsService.updateversion();
		resultJson.setSuccess(true);
		resultJson.setMsg("保存成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}
	public String delete() throws Exception {
		this.calendarService.deleteCalendarCategoryByIds(this.calendarCategoryVO.getIds());
		this.versionsService.updateversion();
		resultJson.setSuccess(true);
		resultJson.setMsg("删除成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, true);
		return null;
	}
	public String update() throws Exception {
		this.calendarCategory.setId(this.calendarCategoryVO.getId());
		this.calendarCategory.setTitle(this.calendarCategoryVO.getTitle());
		this.calendarCategory.setBeginDate(this.calendarCategoryVO.getBeginDate());
		this.calendarCategory.setWeeknum(this.calendarCategoryVO.getWeeknum());
		this.calendarCategory.setPublisher(loginUser.getName());
		this.calendarService.updateCalendarCategory(this.calendarCategory);
		this.versionsService.updateversion();
		resultJson.setSuccess(true);
		resultJson.setMsg("修改成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}

}
