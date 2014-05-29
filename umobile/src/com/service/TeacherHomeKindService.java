package com.service;

import com.model.publicservice.TeacherHomeKind;
import com.vo.bean.TeacherHomeKindVO;

public interface TeacherHomeKindService {
	public abstract String findAllTeacherHomeKind(TeacherHomeKindVO vo);

	String saveTeacherHomeKind(TeacherHomeKindVO vo);

	String deleteTeacherHomeKind(TeacherHomeKindVO vo);

	String updateTeacherHomeKind(TeacherHomeKindVO vo);
	
	TeacherHomeKind findTeacherHomeKindById(TeacherHomeKindVO vo);

}