package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.dao.StudentFieldDAO;
import com.google.gson.Gson;
import com.model.publicservice.StudentField;
import com.model.publicservice.StudentFieldKind;
import com.model.publicservice.StudentFieldKindTree;
import com.model.publicservice.StudentFieldLazy;
import com.model.publicservice.TeacherHome;
import com.service.StudentFieldService;
import com.vo.bean.StudentFieldVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;

@Component("studentFieldService")
public class StudentFieldServiceImpl implements StudentFieldService {
	private Gson gson = new Gson();
	private StudentFieldDAO studentFieldDAO;
	
	public StudentFieldDAO getStudentFieldDAO() {
		return studentFieldDAO;
	}
	@Resource
	public void setStudentFieldDAO(StudentFieldDAO studentFieldDAO) {
		this.studentFieldDAO = studentFieldDAO;
	}
	@Override
	public String findAllStudentField(StudentFieldVO vo){
		List<StudentField> rows = this.studentFieldDAO.findAllStudentField(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder());		
		long total = studentFieldDAO.findTotal(); 
        SuperJson<StudentField> superJson = new SuperJson<StudentField>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	@Override
	public String saveStudentField(StudentFieldVO vo) {
		StudentFieldLazy activity = vo.getStudentfieldLazy();
		activity.setPublisher(vo.getLoginUser().getName());
		
		this.studentFieldDAO.saveStudentField(activity);
		return null;
	}
	@Override
	public String deleteStudentField(StudentFieldVO vo) {
				String[] idsArray = (vo.getIds()).split("&");
				for(int i=0;i<idsArray.length;i++){
					StudentField studentField = this.studentFieldDAO.findStudentFieldById(Long.valueOf(idsArray[i]));
					this.studentFieldDAO.deleteStudentField(studentField);
				}
				
				return null;
	}
	@Override
	public String updateStudentField(StudentFieldVO vo) {
		StudentFieldLazy activity = vo.getStudentfieldLazy();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.studentFieldDAO.mergeStudentField(activity);
		return null;
	}
	
	@Override
	public StudentField findStudentFieldById(StudentFieldVO vo){
		return this.studentFieldDAO.findStudentFieldById(vo.getId());
	}
	
	@Override
	public String searchStudentField(StudentFieldVO vo){
		
		List<StudentField> rows=new ArrayList<StudentField>();
		StudentFieldKindTree ktree=null;
		long total;
		
		if(vo.getKindId()!=0){
			List<StudentFieldLazy> rows_lazy = this.studentFieldDAO.searchAllStudentField(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),vo.getKindId()); 
       
			if(rows_lazy.size()>0){
				ktree = studentFieldDAO.findKindTree(((StudentFieldLazy)rows_lazy.get(0)).getKindId());
			}
			for(int i=0;i<rows_lazy.size();i++)
			{
				StudentField e=new StudentField();
				e.setId(((StudentFieldLazy)rows_lazy.get(i)).getId());
				e.setTitle(((StudentFieldLazy)rows_lazy.get(i)).getTitle());
        		e.setContent(((StudentFieldLazy)rows_lazy.get(i)).getContent());
        		e.setCreateTime(((StudentFieldLazy)rows_lazy.get(i)).getCreateTime());
        		e.setPublisher(((StudentFieldLazy)rows_lazy.get(i)).getPublisher());
        		e.setStudentFieldKindTree(ktree);        	
        		rows.add(e);
			}
			total = studentFieldDAO.searchTotal(vo.getKindId()); 
		}else{
			rows = this.studentFieldDAO.findAllStudentField(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder());
			total = studentFieldDAO.findTotal(); 
		}

        SuperJson<StudentField> superJson = new SuperJson<StudentField>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	
	@Override
	public String updateEditKind(StudentFieldVO vo){
		//TeacherHomeLazy activity = vo.getTeacherhomeLazy();
		String[] ids = vo.getIds().split("&");
		for(int i=0;i<ids.length;i++){
			StudentFieldLazy studentFieldLazy = this.studentFieldDAO.findStudentFieldLazyById(Long.valueOf(ids[i]));
			studentFieldLazy.setKindId(vo.getKindId());
			studentFieldLazy.setPublisher(vo.getLoginUser().getName());
			studentFieldLazy.setCreateTime(null);
			this.studentFieldDAO.mergeStudentField(studentFieldLazy);
		}
		return null;
	}
}
