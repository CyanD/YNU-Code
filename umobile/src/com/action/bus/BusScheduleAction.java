package com.action.bus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.bus.BusScheduleLazy;
import com.opensymphony.xwork2.ModelDriven;
import com.service.BusService;
import com.vo.bean.BusScheduleVO;
import com.ynu.zjx.SuperAction;
import com.ynu.zjx.SuperJson;
@Component("busScheduleAction")
@Scope("prototype")
public class BusScheduleAction extends SuperAction implements ModelDriven<BusScheduleVO>{
	private BusScheduleVO busScheduleVO = new BusScheduleVO();
	private BusService busService;
	private BusScheduleLazy busScheduleLazy = new BusScheduleLazy();
	public BusService getBusService() {
		return busService;
	}

	@Override
	public BusScheduleVO getModel() {
		return this.busScheduleVO;
	}

	public BusScheduleVO getBusScheduleVO() {
		return busScheduleVO;
	}
	public void setBusScheduleVO(BusScheduleVO busScheduleVO) {
		this.busScheduleVO = busScheduleVO;
	}
	@Resource
	public void setBusService(BusService busService) {
		this.busService = busService;
	}

	public String show() throws Exception {
        @SuppressWarnings("unchecked")
		List<BusScheduleLazy> rows = (List<BusScheduleLazy>)this.busService.findBusScheduleByPage(busScheduleVO.getPid(),busScheduleVO.getRows()*(busScheduleVO.getPage()-1), busScheduleVO.getRows(),busScheduleVO.getSort(),busScheduleVO.getOrder()); 
        long total = this.busService.findBusScheduleTotal(busScheduleVO.getPid()); 
        SuperJson<BusScheduleLazy> superJson = new SuperJson<BusScheduleLazy>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		this.jsonString = toJsonString(superJson);
		outputJson(this.jsonString, true);
		
		return null;
	}
	
	public String save() throws Exception {
		this.busScheduleLazy.setPid(busScheduleVO.getPid());
		this.busScheduleLazy.setStartTime(this.busScheduleVO.getStartTime());
		this.busScheduleLazy.setCostTime(this.busScheduleVO.getCostTime());
		this.busScheduleLazy.setPublisher(loginUser.getName());//以后用session取
		try {
			this.busService.savebusScheduleLazy(this.busScheduleLazy);
			resultJson.setSuccess(true);
			resultJson.setMsg("保存成功！");
		} catch (Exception e) {
			resultJson.setSuccess(false);
			resultJson.setMsg("保存失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString, false);
		}
		
		return null;
		
	}
	public String delete() throws Exception {
		try {
			this.busService.deletebusScheduleByIds(this.busScheduleVO.getIds());
			resultJson.setSuccess(true);
			resultJson.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("删除失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString,true);
		}
		return null;
	}
	public String update() throws Exception {
		this.busScheduleLazy.setId(this.busScheduleVO.getId());
		this.busScheduleLazy.setPid(busScheduleVO.getPid());
		this.busScheduleLazy.setStartTime(this.busScheduleVO.getStartTime());
		this.busScheduleLazy.setCostTime(this.busScheduleVO.getCostTime());
		this.busScheduleLazy.setPublisher(loginUser.getName());//以后用session取
		try {
			this.busService.updatebusScheduleLazy(this.busScheduleLazy);
			resultJson.setSuccess(true);
			resultJson.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setSuccess(false);
			resultJson.setMsg("修改失败！请稍后重试！");
		}finally{
			this.jsonString = toJsonString(resultJson);
			outputJson(this.jsonString, false);
		}
		return null;
	}
}
