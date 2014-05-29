package com.service;

import java.util.List;

import com.model.bus.BusRoute;
import com.model.bus.BusScheduleLazy;

public interface BusService {

	long findBusRouteTotal();

	List<?> findBusRouteByPage(int i, int rows, String sort, String order);

	void saveBusRoute(BusRoute busRoute);

	void deleteBusRouteByIds(String ids);

	void updateBusRoute(BusRoute busRoute);

	List<?> findBusScheduleByPage(long pid,int i, int rows,String sort, String order);

	long findBusScheduleTotal(long pid);

	void savebusScheduleLazy(BusScheduleLazy busScheduleLazy);

	void deletebusScheduleByIds(String ids);


	void updatebusScheduleLazy(BusScheduleLazy busScheduleLazy);

}
