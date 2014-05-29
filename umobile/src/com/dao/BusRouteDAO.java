package com.dao;

import java.util.List;

import com.model.bus.BusRoute;

public interface BusRouteDAO {

	public abstract void save(BusRoute transientInstance);

	public abstract void delete(BusRoute persistentInstance);

	public abstract BusRoute findById(java.lang.Long id);

	public abstract List<BusRoute> findByExample(BusRoute instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<BusRoute> findByName(Object name);

	public abstract List<BusRoute> findByStartStation(Object startStation);

	public abstract List<BusRoute> findByEndStation(Object endStation);

	public abstract List<BusRoute> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract BusRoute merge(BusRoute detachedInstance);

	public abstract void attachDirty(BusRoute instance);

	public abstract void attachClean(BusRoute instance);

	public abstract long findTotal();

	public abstract List<?> findByPage(int i, int rows, String sort,
			String order);

	public abstract void deleteByIds(String ids);

	void update(BusRoute busRoute);

}