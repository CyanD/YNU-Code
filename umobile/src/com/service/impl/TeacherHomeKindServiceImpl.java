package com.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.TeacherHomeKindDAO;
import com.google.gson.Gson;
import com.model.course.CourseCategoryTreeGrid;
import com.model.publicservice.PublicserviceActivity;
import com.model.publicservice.TeacherHomeKind;
import com.service.TeacherHomeKindService;
import com.vo.bean.NewsNewsVO;
import com.vo.bean.PublicserviceActivityVO;
import com.vo.bean.TeacherHomeKindVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;

@Component("teacherHomeKindService")
public class TeacherHomeKindServiceImpl implements TeacherHomeKindService {
	private Gson gson = new Gson();
	private TeacherHomeKindDAO teacherHomeKindDAO;
	
	public TeacherHomeKindDAO getTeacherHomeKindDAO() {
		return teacherHomeKindDAO;
	}
	@Resource
	public void setTeacherHomeKindDAO(TeacherHomeKindDAO teacherHomeKindDAO) {
		this.teacherHomeKindDAO = teacherHomeKindDAO;
	}
	
	@Override
	public String findAllTeacherHomeKind(TeacherHomeKindVO vo) {
		List<TeacherHomeKind> rows = this.teacherHomeKindDAO.findAllTeacherHomeKind(); 
        long total = teacherHomeKindDAO.findTotal(); 
        SuperJson<TeacherHomeKind> superJson = new SuperJson<TeacherHomeKind>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	@Override
	public String saveTeacherHomeKind(TeacherHomeKindVO vo) {
		TeacherHomeKind kind = vo.getTeacherhomeKind();
		kind.setPublisher(vo.getLoginUser().getName());
		this.teacherHomeKindDAO.saveTeacherHomeKind(kind);
		return null;
	}
	@Override
	public String deleteTeacherHomeKind(TeacherHomeKindVO vo) {
		//this.teacherHomeKindDAO.deleteTeacherHomeKind(vo.getTeacherhomeKind());
		String[] idsArray = (vo.getIds()).split("&");
		for(int i=0;i<idsArray.length;i++){
			TeacherHomeKind teacherHomeKind = this.teacherHomeKindDAO.findTeacherHomeKindById(Long.valueOf(idsArray[i]));
			this.teacherHomeKindDAO.deleteTeacherHomeKind(teacherHomeKind);
		}
		
		return null;
	}
	@Override
	public String updateTeacherHomeKind(TeacherHomeKindVO vo) {
		TeacherHomeKind activity = vo.getTeacherhomeKind();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.teacherHomeKindDAO.updateTeacherHomeKind(activity);
		return null;
	}
	
	@Override
	public TeacherHomeKind findTeacherHomeKindById(TeacherHomeKindVO vo) {
		return this.teacherHomeKindDAO.findTeacherHomeKindById(vo.getId());
	}

}
