package com.service;

import com.model.publicservice.YnuStory;
import com.vo.bean.YnuStoryVO;

public interface YnuStoryService {
	public abstract String findAllYnuStory(YnuStoryVO vo);

	String saveYnuStory(YnuStoryVO vo);

	String deleteYnuStory(YnuStoryVO vo);

	String updateYnuStory(YnuStoryVO vo);
	
	YnuStory findYnuStoryById(YnuStoryVO vo);
	public abstract String saveYnuStoryHeadBack(YnuStoryVO vo);
	public abstract String findSearchYnuStoryByName(YnuStoryVO vo);
}