package com.dao;

import java.util.List;

import com.model.publicservice.PublicserviceActivity;

public interface PublicserviceActivityDAO {
	List<PublicserviceActivity> findAllActivity(int i, int rows, String sort, String order);

	long findTotal();

	void saveActivity(PublicserviceActivity activity);

	PublicserviceActivity findActivityById(Long id);

	void deleteActivity(PublicserviceActivity activity);

	void mergeActivity(PublicserviceActivity activity);

}
