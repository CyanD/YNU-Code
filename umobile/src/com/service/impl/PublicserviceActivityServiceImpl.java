package com.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.PublicserviceActivityDAO;
import com.model.publicservice.PublicserviceActivity;
import com.service.PublicserviceActivityService;
import com.vo.bean.PublicserviceActivityVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;

@Component("publicserviceActivityService")
public class PublicserviceActivityServiceImpl implements PublicserviceActivityService {
	private PublicserviceActivityDAO publicserviceActivityDAO;
	public PublicserviceActivityDAO getPublicserviceActivityDAO() {
		return publicserviceActivityDAO;
	}
	@Resource
	public void setPublicserviceActivityDAO(
			PublicserviceActivityDAO publicserviceActivityDAO) {
		this.publicserviceActivityDAO = publicserviceActivityDAO;
	}
	
	@Override
	public String findAllActivity(PublicserviceActivityVO vo) {
		List<PublicserviceActivity> rows = this.publicserviceActivityDAO.findAllActivity(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = publicserviceActivityDAO.findTotal(); 
        SuperJson<PublicserviceActivity> superJson = new SuperJson<PublicserviceActivity>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveActivity(PublicserviceActivityVO vo) {
		PublicserviceActivity activity = vo.getPublicserviceActivity();
		activity.setPublisher(vo.getLoginUser().getName());
		this.publicserviceActivityDAO.saveActivity(activity);
		return null;
	}
	@Override
	public String deleteActivity(PublicserviceActivityVO vo) {
		this.publicserviceActivityDAO.deleteActivity(vo.getPublicserviceActivity());
		return null;
	}
	@Override
	public String updateActivity(PublicserviceActivityVO vo) {
		PublicserviceActivity activity = vo.getPublicserviceActivity();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.publicserviceActivityDAO.mergeActivity(activity);
		return null;
	}
	
	@Override
	public PublicserviceActivity findPublicserviceActivityById(PublicserviceActivityVO vo) {
		return this.publicserviceActivityDAO.findActivityById(vo.getId());
	}
	
	@Override
	public void doTask(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		PublicserviceActivity ac=new PublicserviceActivity();
		ac.setTitle("hahahah啦啦啦啦啦");
		ac.setBeginDate("2012-09-09");
		ac.setEndDate("2012-10-10");
		ac.setTimePoint("6:30:00");
		ac.setLocation("test");
		ac.setCreateTime(sdf.format(new Date()));
		ac.setPublisher("zjx");
		this.publicserviceActivityDAO.saveActivity(ac);
	}

}
