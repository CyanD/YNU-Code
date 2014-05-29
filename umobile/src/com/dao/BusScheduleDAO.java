package com.dao;

import java.util.List;

import com.model.bus.BusSchedule;
import com.model.bus.BusScheduleLazy;

public interface BusScheduleDAO {

	public abstract void save(BusSchedule transientInstance);

	public abstract void delete(BusSchedule persistentInstance);

	public abstract BusSchedule findById(java.lang.Long id);

	public abstract List<BusSchedule> findByExample(BusSchedule instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List<BusSchedule> findByPublisher(Object publisher);

	public abstract List findAll();

	public abstract BusSchedule merge(BusSchedule detachedInstance);

	public abstract void attachDirty(BusSchedule instance);

	public abstract void attachClean(BusSchedule instance);

	public abstract List<?> findByPage(long rid, int i, int rows, String sort,
			String order);

	public abstract long findTotal(long rid);

	public abstract void saveLazy(BusScheduleLazy busScheduleLazy);

	public abstract void deleteByIds(String ids);

	public abstract void updateLazy(BusScheduleLazy busScheduleLazy);

}