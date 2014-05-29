package com.service;

import com.model.publicservice.PublicserviceActivity;
import com.vo.bean.PublicserviceActivityVO;

public interface PublicserviceActivityService {
	public abstract String findAllActivity(PublicserviceActivityVO vo);

	String saveActivity(PublicserviceActivityVO vo);

	String deleteActivity(PublicserviceActivityVO vo);

	String updateActivity(PublicserviceActivityVO vo);
	
	PublicserviceActivity findPublicserviceActivityById(PublicserviceActivityVO vo);
	
    void doTask();
}
