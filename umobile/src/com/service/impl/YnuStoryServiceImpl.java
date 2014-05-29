package com.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;


import com.dao.YnuStoryDAO;
import com.dao.YnuStoryHeadBackDAO;
import com.google.gson.Gson;
import com.model.publicservice.YnuStory;
import com.model.publicservice.YnuStoryHeadBack;
import com.service.YnuStoryService;
import com.util.Sys;
import com.vo.bean.YnuStoryVO;
import com.ynu.zjx.SuperJson;

@Component("ynuStoryService")
public class YnuStoryServiceImpl implements YnuStoryService {
	private Gson gson = new Gson();
	private YnuStoryDAO ynuStoryDAO;
	private YnuStoryHeadBackDAO ynuStoryHeadBackDAO;
	
	public YnuStoryHeadBackDAO getYnuStoryHeadBackDAO() {
		return ynuStoryHeadBackDAO;
	}
	@Resource
	public void setYnuStoryHeadBackDAO(
			YnuStoryHeadBackDAO ynuStoryHeadBackDAO) {
		this.ynuStoryHeadBackDAO = ynuStoryHeadBackDAO;
	}
	public YnuStoryDAO getYnuStoryDAO() {
		return ynuStoryDAO;
	}
	@Resource
	public void setYnuStoryDAO(YnuStoryDAO ynuStoryDAO) {
		this.ynuStoryDAO = ynuStoryDAO;
	}
	@Override
	public String findAllYnuStory(YnuStoryVO vo){
		List<YnuStory> rows = this.ynuStoryDAO.findAllYnuStory(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder());		
		long total = ynuStoryDAO.findTotal(); 
        SuperJson<YnuStory> superJson = new SuperJson<YnuStory>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return gson.toJson(superJson);
	}
	@Override
	public String saveYnuStory(YnuStoryVO vo) {
		YnuStory activity = vo.getYnuStory();
		activity.setPublisher(vo.getLoginUser().getName());	
		
		if(activity.getHeadpath()==null||"".equals(activity.getHeadpath())){
			//activity.setHeadpath(Sys.DEFAULTADDRESSBOOKHEADPATH);
			activity.setHeadpath("");
		}else{
			this.ynuStoryHeadBackDAO.deleteByPath(activity.getHeadpath());
		}
		
		this.ynuStoryDAO.saveYnuStory(activity);
		return null;
	}
	@Override
	public String deleteYnuStory(YnuStoryVO vo) {
				String[] idsArray = (vo.getIds()).split("&");
				for(int i=0;i<idsArray.length;i++){
					YnuStory ynuStory = this.ynuStoryDAO.findYnuStoryById(Long.valueOf(idsArray[i]));
					this.ynuStoryDAO.deleteYnuStory(ynuStory);
				}
				
				return null;
	}
	@Override
	public String updateYnuStory(YnuStoryVO vo) {
		/*YnuStory activity = vo.getYnuStory();
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		this.ynuStoryDAO.mergeYnuStory(activity);*/
		YnuStory activity = this.ynuStoryDAO.findYnuStoryById(vo.getId());
		activity.setPublisher(vo.getLoginUser().getName());
		activity.setCreateTime(null);
		activity.setContent(vo.getContent());
		activity.setTitle(vo.getTitle());
		activity.setHappenTime(vo.getHappenTime());
		activity.setHeadpath(vo.getHeadpath());
		this.ynuStoryDAO.mergeYnuStory(activity);
		return null;
	}
	
	@Override
	public YnuStory findYnuStoryById(YnuStoryVO vo){
		return this.ynuStoryDAO.findYnuStoryById(vo.getId());
	}
	@Override
	public String saveYnuStoryHeadBack(YnuStoryVO vo) {
		YnuStoryHeadBack ynuStoryHeadBack = new YnuStoryHeadBack();
		ynuStoryHeadBack.setPath(vo.getHeadpath());
		this.ynuStoryHeadBackDAO.save(ynuStoryHeadBack);
		return null;
	}
	@Override
    public String findSearchYnuStoryByName(YnuStoryVO vo) {

    	//var keyValue=name+':'+value;
    	String keyValue=vo.getParam();
    	String list[]=keyValue.split(":");
    	if(list.length<2){
    		return findAllYnuStory(vo);
    	}else{
    		String fieldName=keyValue.split(":")[0];
        	String fieldValue=keyValue.split(":")[1];
 
        	if (fieldName.equals("all"))
        		return findAllYnuStory(vo);
        	else{
        	List<YnuStory> rows = this.ynuStoryDAO.findSearchYnuStory(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),fieldName,fieldValue);
        	long total = ynuStoryDAO.searchTotal(fieldName,fieldValue);
            SuperJson<YnuStory> superJson = new SuperJson<YnuStory>();
            superJson.setRows(rows);
            superJson.setTotal(total);
    		return gson.toJson(superJson);
        	}
    	}	
    }
}
