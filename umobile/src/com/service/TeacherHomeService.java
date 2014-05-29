package com.service;

import com.model.publicservice.TeacherHome;
import com.vo.bean.TeacherHomeVO;

public interface TeacherHomeService {
	public abstract String findAllTeacherHome(TeacherHomeVO vo);

	String saveTeacherHome(TeacherHomeVO vo);

	String deleteTeacherHome(TeacherHomeVO vo);

	String updateTeacherHome(TeacherHomeVO vo);
	
	TeacherHome findTeacherHomeById(TeacherHomeVO vo);
	
	String searchTeacherHome(TeacherHomeVO vo);
	
	String updateEditKind(TeacherHomeVO vo);
}