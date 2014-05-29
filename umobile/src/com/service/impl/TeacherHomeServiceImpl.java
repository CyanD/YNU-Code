package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.TeacherHomeDAO;
import com.google.gson.Gson;
import com.model.publicservice.TeacherHome;
import com.model.publicservice.TeacherHomeKind;
import com.model.publicservice.TeacherHomeKindTree;
import com.model.publicservice.TeacherHomeLazy;
import com.service.TeacherHomeService;
import com.vo.bean.TeacherHomeVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;

@Component("teacherHomeService")
public class TeacherHomeServiceImpl implements TeacherHomeService {
	private Gson gson = new Gson();
	private TeacherHomeDAO teacherHomeDAO;
	
	public TeacherHomeDAO getTeacherHomeDAO() {
		return teacherHomeDAO;
	}
	@Resource
	public void setTeacherHomeDAO(TeacherHomeDAO teacherHomeDAO) {
		this.teacherHomeDAO = teacherHomeDAO;
	}
	
	@Override
	public String findAllTeacherHome(TeacherHomeVO vo) {
		List<TeacherHome> rows = this.teacherHomeDAO.findAllTeacherHome(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 	
		long total = teacherHomeDAO.findTotal(); 
        SuperJson<TeacherHome> superJson = new SuperJson<TeacherHome>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	@Override
	public String saveTeacherHome(TeacherHomeVO vo) {
		TeacherHomeLazy activity = vo.getTeacherhomeLazy();
		activity.setPublisher(vo.getLoginUser().getName());
		
		this.teacherHomeDAO.saveTeacherHome(activity);
		return null;
	}
	@Override
	public String deleteTeacherHome(TeacherHomeVO vo) {
		//this.teacherHomeDAO.deleteTeacherHome(vo.getTeacherhomeLazy());
				String[] idsArray = (vo.getIds()).split("&");
				for(int i=0;i<idsArray.length;i++){
					TeacherHome teacherHome = this.teacherHomeDAO.findTeacherHomeById(Long.valueOf(idsArray[i]));
					this.teacherHomeDAO.deleteTeacherHome(teacherHome);
				}
				
				return null;
	}
	@Override
	public String updateTeacherHome(TeacherHomeVO vo) {
		TeacherHomeLazy activity = vo.getTeacherhomeLazy();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.teacherHomeDAO.mergeTeacherHome(activity);
		return null;
	}
	
	@Override
	public TeacherHome findTeacherHomeById(TeacherHomeVO vo) {
		return this.teacherHomeDAO.findTeacherHomeById(vo.getId());
	}
	
	@Override
	public String searchTeacherHome(TeacherHomeVO vo){
		
		List<TeacherHome> rows=new ArrayList<TeacherHome>();
		TeacherHomeKindTree ktree=null;
		long total;
		
		if(vo.getKindId()!=0){
			List<TeacherHomeLazy> rows_lazy = this.teacherHomeDAO.searchAllTeacherHome(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),vo.getKindId()); 
       
			if(rows_lazy.size()>0){
				ktree = teacherHomeDAO.findKindTree(((TeacherHomeLazy)rows_lazy.get(0)).getKindId());
			}
			for(int i=0;i<rows_lazy.size();i++)
			{
				TeacherHome e=new TeacherHome();
				e.setId(((TeacherHomeLazy)rows_lazy.get(i)).getId());
				e.setTitle(((TeacherHomeLazy)rows_lazy.get(i)).getTitle());
        		e.setContent(((TeacherHomeLazy)rows_lazy.get(i)).getContent());
        		e.setCreateTime(((TeacherHomeLazy)rows_lazy.get(i)).getCreateTime());
        		e.setPublisher(((TeacherHomeLazy)rows_lazy.get(i)).getPublisher());
        		//TeacherHomeKindTree ktree = teacherHomeDAO.findKindTree(((TeacherHomeLazy)rows_lazy.get(i)).getKindId());
        		e.setKindTree(ktree);        	
        		rows.add(e);
			}
			total = teacherHomeDAO.searchTotal(vo.getKindId()); 
		}else{
			rows = this.teacherHomeDAO.findAllTeacherHome(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder());
			total = teacherHomeDAO.findTotal(); 
		}

        SuperJson<TeacherHome> superJson = new SuperJson<TeacherHome>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	
	@Override
	public String updateEditKind(TeacherHomeVO vo) {
		//TeacherHomeLazy activity = vo.getTeacherhomeLazy();
		String[] ids = vo.getIds().split("&");
		for(int i=0;i<ids.length;i++){
			TeacherHomeLazy teacherHomeLazy = this.teacherHomeDAO.findTeacherHomeLazyById(Long.valueOf(ids[i]));
			teacherHomeLazy.setKindId(vo.getKindId());
			teacherHomeLazy.setPublisher(vo.getLoginUser().getName());
			teacherHomeLazy.setCreateTime(null);
			this.teacherHomeDAO.mergeTeacherHome(teacherHomeLazy);
		}
		return null;
	}
}
