package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.BusRouteDAO;
import com.dao.BusScheduleDAO;
import com.model.bus.BusRoute;
import com.model.bus.BusScheduleLazy;
import com.service.BusService;
@Component("busService")
public class BusServiceImpl implements BusService{
	private BusRouteDAO busRouteDAO;
	private BusScheduleDAO busScheduleDAO;

	public BusScheduleDAO getBusScheduleDAO() {
		return busScheduleDAO;
	}
	@Resource
	public void setBusScheduleDAO(BusScheduleDAO busScheduleDAO) {
		this.busScheduleDAO = busScheduleDAO;
	}
	public BusRouteDAO getBusRouteDAO() {
		return busRouteDAO;
	}
	@Resource
	public void setBusRouteDAO(BusRouteDAO busRouteDAO) {
		this.busRouteDAO = busRouteDAO;
	}

	@Override
	public long findBusRouteTotal() {
		return busRouteDAO.findTotal();
	}

	@Override
	public List<?> findBusRouteByPage(int i, int rows, String sort,
			String order) {
		return busRouteDAO.findByPage(i,rows, sort,order);
	}
	@Override
	public void saveBusRoute(BusRoute busRoute) {
		this.busRouteDAO.save(busRoute);
		
	}
	@Override
	public void deleteBusRouteByIds(String ids) {
		this.busRouteDAO.deleteByIds(ids);
	}
	@Override
	public void updateBusRoute(BusRoute busRoute) {
		this.busRouteDAO.update(busRoute);
	}
	@Override
	public List<?> findBusScheduleByPage(long pid, int i, int rows,
			String sort, String order) {
		
		return this.busScheduleDAO.findByPage(pid,i,rows, sort,order);
	}
	@Override
	public long findBusScheduleTotal(long pid) {
		
		return this.busScheduleDAO.findTotal(pid);
	}
	@Override
	public void savebusScheduleLazy(BusScheduleLazy busScheduleLazy) {
		this.busScheduleDAO.saveLazy(busScheduleLazy);
	}
	@Override
	public void deletebusScheduleByIds(String ids) {
		this.busScheduleDAO.deleteByIds(ids);
	}
	@Override
	public void updatebusScheduleLazy(BusScheduleLazy busScheduleLazy) {
		this.busScheduleDAO.updateLazy(busScheduleLazy);
	}
	

}
