package com.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.dao.StudentFieldKindDAO;
import com.google.gson.Gson;
import com.model.publicservice.StudentFieldKind;
import com.service.StudentFieldKindService;
import com.vo.bean.StudentFieldKindVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;

@Component("studentFieldKindService")
public class StudentFieldKindServiceImpl implements StudentFieldKindService {
	private Gson gson = new Gson();
	private StudentFieldKindDAO studentFieldKindDAO;
	
	public StudentFieldKindDAO getStudentFieldKindDAO() {
		return studentFieldKindDAO;
	}
	@Resource
	public void setStudentFieldKindDAO(StudentFieldKindDAO studentFieldKindDAO) {
		this.studentFieldKindDAO = studentFieldKindDAO;
	}
	
	@Override
	public String findAllStudentFieldKind(StudentFieldKindVO vo) {
		List<StudentFieldKind> rows = this.studentFieldKindDAO.findAllStudentFieldKind(); 
        long total = studentFieldKindDAO.findTotal(); 
        SuperJson<StudentFieldKind> superJson = new SuperJson<StudentFieldKind>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	@Override
	public String saveStudentFieldKind(StudentFieldKindVO vo) {
		StudentFieldKind kind = vo.getStudentFieldKind();
		kind.setPublisher(vo.getLoginUser().getName());
		this.studentFieldKindDAO.saveStudentFieldKind(kind);
		return null;
	}
	@Override
	public String deleteStudentFieldKind(StudentFieldKindVO vo) {
		String[] idsArray = (vo.getIds()).split("&");
		for(int i=0;i<idsArray.length;i++){
			StudentFieldKind studentFieldKind = this.studentFieldKindDAO.findStudentFieldKindById(Long.valueOf(idsArray[i]));
			this.studentFieldKindDAO.deleteStudentFieldKind(studentFieldKind);
		}
		
		return null;
	}
	@Override
	public String updateStudentFieldKind(StudentFieldKindVO vo){
		StudentFieldKind activity = vo.getStudentFieldKind();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.studentFieldKindDAO.updateStudentFieldKind(activity);
		return null;
	}
	
	@Override
	public StudentFieldKind findStudentFieldKindById(StudentFieldKindVO vo){
		return this.studentFieldKindDAO.findStudentFieldKindById(vo.getId());
	}

}
