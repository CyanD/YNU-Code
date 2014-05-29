package com.action.bus;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.bus.BusRoute;
import com.opensymphony.xwork2.ModelDriven;
import com.service.BusService;
import com.vo.bean.BusRouteVO;
import com.ynu.zjx.SuperAction;
import com.ynu.zjx.SuperJson;
@Component("busRouteAction")
@Scope("prototype")
public class BusRouteAction extends SuperAction implements ModelDriven<BusRouteVO>{
	private BusRouteVO busRouteVO = new BusRouteVO();
	private BusService busService;
	private BusRoute busRoute = new BusRoute();
	public BusService getBusService() {
		return busService;
	}

	@Override
	public BusRouteVO getModel() {
		return this.busRouteVO;
	}

	public BusRouteVO getBusRouteVO() {
		return busRouteVO;
	}
	public void setBusRouteVO(BusRouteVO busRouteVO) {
		this.busRouteVO = busRouteVO;
	}
	@Resource
	public void setBusService(BusService busService) {
		this.busService = busService;
	}

	@SuppressWarnings("unchecked")
	public String show() throws Exception {
        List<BusRoute> rows = (List<BusRoute>)this.busService.findBusRouteByPage(busRouteVO.getRows()*(busRouteVO.getPage()-1), busRouteVO.getRows(),busRouteVO.getSort(),busRouteVO.getOrder());   
    	long total = this.busService.findBusRouteTotal(); 
        SuperJson<BusRoute> superJson = new SuperJson<BusRoute>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		this.jsonString = toJsonString(superJson);
		super.outputJson(jsonString, true);
		return null;
	}
	public String save() throws Exception {
		this.busRoute.setName(busRouteVO.getName());
		this.busRoute.setStartStation(this.busRouteVO.getStartStation());
		this.busRoute.setEndStation(this.busRouteVO.getEndStation());
		this.busRoute.setPublisher(loginUser.getName());
		this.busRoute.setByCarPlace(this.busRouteVO.getByCarPlace());
		this.busRoute.setViaStation(this.busRouteVO.getViaStation());
		this.busService.saveBusRoute(this.busRoute);
		resultJson.setSuccess(true);
		resultJson.setMsg("保存成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}
	public String delete() throws Exception {
		this.busService.deleteBusRouteByIds(this.busRouteVO.getIds());
		resultJson.setSuccess(true);
		resultJson.setMsg("删除成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, true);
		return null;
	}
	public String update() throws Exception {
		this.busRoute.setId(this.busRouteVO.getId());
		this.busRoute.setName(this.busRouteVO.getName());
		this.busRoute.setStartStation(this.busRouteVO.getStartStation());
		this.busRoute.setEndStation(this.busRouteVO.getEndStation());
		this.busRoute.setByCarPlace(this.busRouteVO.getByCarPlace());
		this.busRoute.setViaStation(this.busRouteVO.getViaStation());
		this.busRoute.setPublisher(loginUser.getName());
		this.busService.updateBusRoute(this.busRoute);
		resultJson.setSuccess(true);
		resultJson.setMsg("修改成功！");
		this.jsonString = toJsonString(resultJson);
		super.outputJson(jsonString, false);
		return null;
	}

}
