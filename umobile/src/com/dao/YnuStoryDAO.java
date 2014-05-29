package com.dao;

import java.util.List;
import com.model.publicservice.YnuStory;

public interface YnuStoryDAO {
	List<YnuStory> findAllYnuStory(int i, int rows, String sort, String order);

	long findTotal();

	void saveYnuStory(YnuStory activity);

	YnuStory findYnuStoryById(Long id);

	void deleteYnuStory(YnuStory activity);

	void mergeYnuStory(YnuStory activity);

    List<YnuStory> findSearchYnuStory(int i, int rows, String sort,String order,String fieldName,String fieldValue);

	long searchTotal(String fieldName,String fieldValue);
}
