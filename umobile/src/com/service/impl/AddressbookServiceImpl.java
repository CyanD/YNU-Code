package com.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.AddressbookAlumniDAO;
import com.dao.AddressbookDepartmentDAO;
import com.dao.AddressbookDeptDAO;
import com.dao.AddressbookPublicDAO;
import com.dao.AddressbookWorkDAO;
import com.model.addressbook.AddressbookPublicTree;
import com.model.addressbook.AddressbookPublicTreeGrid;
import com.model.addressbook.alumni.AddressbookAlumni;
import com.model.addressbook.work.AddressbookDepartmentTreeGrid;
import com.model.addressbook.work.AddressbookDeptTree;
import com.model.addressbook.work.AddressbookDeptTreeGrid;
import com.model.addressbook.work.AddressbookWorkGrid;
import com.model.addressbook.work.AddressbookWorkLazy;
import com.service.AddressbookService;
import com.util.Sys;
import com.vo.bean.AddressbookAlumniVO;
import com.vo.bean.AddressbookDepartmentVO;
import com.vo.bean.AddressbookDeptVO;
import com.vo.bean.AddressbookPublicVO;
import com.vo.bean.AddressbookWorkVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.ResultJson;
import com.ynu.zjx.SuperJson;


@Component("addressbookService")
public class AddressbookServiceImpl implements AddressbookService{
	private AddressbookPublicDAO addressbookPublicDAO;
	private AddressbookDeptDAO addressbookDeptDAO;
	private AddressbookDepartmentDAO addressbookDepartmentDAO;
	private AddressbookWorkDAO addressbookWorkDAO;
	private AddressbookAlumniDAO addressbookAlumniDAO;
	public AddressbookPublicDAO getAddressbookPublicDAO() {
		return addressbookPublicDAO;
	}
	@Resource
	public void setAddressbookPublicDAO(AddressbookPublicDAO addressbookPublicDAO) {
		this.addressbookPublicDAO = addressbookPublicDAO;
	}
	public AddressbookDeptDAO getAddressbookDeptDAO() {
		return addressbookDeptDAO;
	}
	@Resource
	public void setAddressbookDeptDAO(AddressbookDeptDAO addressbookDeptDAO) {
		this.addressbookDeptDAO = addressbookDeptDAO;
	}
	public AddressbookDepartmentDAO getAddressbookDepartmentDAO() {
		return addressbookDepartmentDAO;
	}
	@Resource
	public void setAddressbookDepartmentDAO(
			AddressbookDepartmentDAO addressbookDepartmentDAO) {
		this.addressbookDepartmentDAO = addressbookDepartmentDAO;
	}
	public AddressbookWorkDAO getAddressbookWorkDAO() {
		return addressbookWorkDAO;
	}
	@Resource
	public void setAddressbookWorkDAO(AddressbookWorkDAO addressbookWorkDAO) {
		this.addressbookWorkDAO = addressbookWorkDAO;
	}
	public AddressbookAlumniDAO getAddressbookAlumniDAO() {
		return addressbookAlumniDAO;
	}
	@Resource
	public void setAddressbookAlumniDAO(AddressbookAlumniDAO addressbookAlumniDAO) {
		this.addressbookAlumniDAO = addressbookAlumniDAO;
	}
	@Override
	public String findAddressbookPublicLazys(AddressbookPublicVO vo) {
		List<AddressbookPublicTreeGrid> rows = this.addressbookPublicDAO.findAllLazy();
		long total = 0l; 
		SuperJson<AddressbookPublicTreeGrid> superJson = new SuperJson<AddressbookPublicTreeGrid>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String findAddressbookPublicLazy(AddressbookPublicVO vo) {
		List<AddressbookPublicTreeGrid> modelLazys = this.addressbookPublicDAO.findLazyByPid(vo.get_parentId());
		return MyUtil.toJson(modelLazys);
	}
	@Override
	public String findAddressbookPublicTree(AddressbookPublicVO vo) {
		List<AddressbookPublicTree> modelTreeRoots = (List<AddressbookPublicTree>)this.addressbookPublicDAO.findTreeRootByPid(null);
		AddressbookPublicTree modelTreeRoot = new AddressbookPublicTree();
		modelTreeRoot.setId(0l);
		modelTreeRoot.setChildren(null);
		modelTreeRoot.setText("无上级部门");
		modelTreeRoot.setIconCls("icon-xiaolian");
		modelTreeRoots.add(modelTreeRoot);
		return MyUtil.toJson(modelTreeRoots);
	}
	@Override
	public String saveAddressbookPublicLazy(AddressbookPublicVO vo) {
		AddressbookPublicTreeGrid modelLazy = vo.getAddressbookPublicTreeGrid();
		modelLazy.setPublisher(vo.getPublisher());
		this.addressbookPublicDAO.saveLazy(modelLazy);
		return null;
	}
	@Override
	public String deleteAddressbookPublicLazyById(AddressbookPublicVO vo) {
		this.addressbookPublicDAO.deleteById(vo.getId());
		return null;
	}
	@Override
	public String updateAddressbookPublicLazy(AddressbookPublicVO vo) {
		AddressbookPublicTreeGrid modelLazy = vo.getAddressbookPublicTreeGrid();
		modelLazy.setPublisher(vo.getPublisher());
		this.addressbookPublicDAO.updateLazy(modelLazy);
		return null;
	}
	@Override
	public String findAddressbookDeptTreeGrid(AddressbookDeptVO vo) {
		List<AddressbookDeptTreeGrid> rows = this.addressbookDeptDAO.findAddressbookDeptTreeGrid(vo.getQueryString());
		long total = 0l; 
		SuperJson<AddressbookDeptTreeGrid> superJson = new SuperJson<AddressbookDeptTreeGrid>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveAddressbookDeptTreeGrid(AddressbookDeptVO vo) {
		AddressbookDeptTreeGrid addressbookDeptTreeGrid = vo.getAddressbookDeptTreeGrid();
		addressbookDeptTreeGrid.setPublisher(vo.getLoginUser().getName());
		this.addressbookDeptDAO.saveAddressbookDeptTreeGrid(addressbookDeptTreeGrid);
		return null;
	}
	@Override
	public String deleteAddressbookDeptTreeGrid(AddressbookDeptVO vo) {
		AddressbookDeptTreeGrid addressbookDeptTreeGrid = this.addressbookDeptDAO.findAddressbookDeptTreeGridById(vo.getId());
		if(addressbookDeptTreeGrid==null){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("该院系已被其他管理员删除！");
			return MyUtil.toJson(resultJson);
		}
		this.addressbookDeptDAO.deleteAddressbookDeptTreeGrid(addressbookDeptTreeGrid);
		return null;
	}
	@Override
	public String updateAddressbookDeptTreeGrid(AddressbookDeptVO vo) {
		AddressbookDeptTreeGrid addressbookDeptTreeGrid = vo.getAddressbookDeptTreeGrid();
		addressbookDeptTreeGrid.setPublisher(vo.getLoginUser().getName());
		addressbookDeptTreeGrid.setCreateTime(null);
		this.addressbookDeptDAO.mergeAddressbookDeptTreeGrid(addressbookDeptTreeGrid);
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String findAddressbookWorkGrids(AddressbookWorkVO vo) {
		List<AddressbookWorkGrid> rows ;
		long total;
		String queryString = "";
		if(vo.getName()!=null&&!vo.getName().trim().isEmpty()){
			queryString+=" name like '%"+vo.getName()+"%' and ";
		}
		if(vo.getPosition()!=null&&!vo.getPosition().trim().isEmpty()){
			queryString+=" position like '%"+vo.getPosition()+"%' and ";
		}
		if(vo.getDepartmentId()!=null){
			queryString+=" addressbookDepartment.id = "+vo.getDepartmentId()+" and ";
		}
		if(!"".equals(queryString)){
			queryString = " where "+queryString.substring(0,queryString.length()-4);
		}
		System.out.println(queryString);
		queryString =  " AddressbookWorkGrid " + queryString;
		rows= (List<AddressbookWorkGrid>)this.addressbookWorkDAO.findListByPage(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),queryString); 
		total = addressbookWorkDAO.findListTotal(queryString); 
		SuperJson<AddressbookWorkGrid> superJson = new SuperJson<AddressbookWorkGrid>();
		superJson.setRows(rows);
		superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveAddressbookWorkLazy(AddressbookWorkVO vo) {
		AddressbookWorkLazy addressbookWorkLazy = vo.getAddressbookWorkLazy();
		if("是".equals(addressbookWorkLazy.getIsLeader())){
			if(addressbookWorkLazy.getDepartmentId()==null){
				ResultJson resultJson = new ResultJson();
				resultJson.setSuccess(false);
				resultJson.setMsg("领导必须选择部门！");
				return MyUtil.toJson(resultJson);
			}
		}else{
			addressbookWorkLazy.setDepartmentId(null);
		}
		AddressbookDeptTree addressbookDeptTree = this.addressbookDeptDAO.findAddressbookDeptTreeById(vo.getDeptId());
		if(addressbookDeptTree==null||!"是".equals(addressbookDeptTree.getIsSystem())){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg(Sys.ERRORMSG);
			return MyUtil.toJson(resultJson);
		}
		addressbookWorkLazy.setPublisher(vo.getLoginUser().getName());
		this.addressbookWorkDAO.saveAddressbookWorkLazy(addressbookWorkLazy);
		return null;
	}
	@Override
	public String deleteAddressbookWorkLazy(AddressbookWorkVO vo) {
		this.addressbookWorkDAO.deleteAddressbookWorkLazy(vo.getAddressbookWorkLazy());
		return null;
	}
	@Override
	public String updateAddressbookWorkLazy(AddressbookWorkVO vo) {
		AddressbookWorkLazy addressbookWorkNew = vo.getAddressbookWorkLazy();
		AddressbookDeptTree addressbookDeptTree = this.addressbookDeptDAO.findAddressbookDeptTreeById(vo.getDeptId());
		if(addressbookDeptTree==null||!"是".equals(addressbookDeptTree.getIsSystem())){
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg(Sys.ERRORMSG);
			return MyUtil.toJson(resultJson);
		}
		addressbookWorkNew.setPublisher(vo.getLoginUser().getName());
		addressbookWorkNew.setCreateTime(null);
		this.addressbookWorkDAO.mergeAddressbookWorkLazy(addressbookWorkNew);
		return null;
	}
	@Override
	public String findAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo) {
		List<AddressbookDepartmentTreeGrid> rows = this.addressbookDepartmentDAO.findAddressbookDepartmentTreeGrid();
		long total = 0l; 
		SuperJson<AddressbookDepartmentTreeGrid> superJson = new SuperJson<AddressbookDepartmentTreeGrid>();
		superJson.setRows(rows);
	    superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo) {
		AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid = vo.getAddressbookDepartmentTreeGrid();
		addressbookDepartmentTreeGrid.setPublisher(vo.getLoginUser().getName());
		this.addressbookDepartmentDAO.saveAddressbookDepartmentTreeGrid(addressbookDepartmentTreeGrid);
		return null;
	}
	@Override
	public String deleteAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo) {
		AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid = this.addressbookDepartmentDAO.findAddressbookDepartmentTreeGridById(vo.getId());
		this.addressbookDepartmentDAO.deleteAddressbookDepartmentTreeGrid(addressbookDepartmentTreeGrid);
		return null;
	}
	@Override
	public String updateAddressbookDepartmentTreeGrid(AddressbookDepartmentVO vo) {
		//AddressbookDepartmentTreeGrid addressbookDepartmentTreeGrid = this.addressbookDepartmentDAO.findAddressbookDepartmentTreeGridById(vo.getId());
		AddressbookDepartmentTreeGrid addressbookDepartmentTreeGridNew = vo.getAddressbookDepartmentTreeGrid();
		addressbookDepartmentTreeGridNew.setPublisher(vo.getLoginUser().getName());
		addressbookDepartmentTreeGridNew.setCreateTime(null);
		this.addressbookDepartmentDAO.updateAddressbookDepartmentTreeGrid(addressbookDepartmentTreeGridNew);
		return null;
	}
	@Override
	public String findAddressbookAlumnis(AddressbookAlumniVO vo) {
		List<AddressbookAlumni> rows = this.addressbookAlumniDAO.findAddressbookAlumnis(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder()); 
        long total = addressbookAlumniDAO.findTotal(); 
        SuperJson<AddressbookAlumni> superJson = new SuperJson<AddressbookAlumni>();
        superJson.setRows(rows);
        superJson.setTotal(total);
		return MyUtil.toJson(superJson);
	}
	@Override
	public String saveAddressbookAlumni(AddressbookAlumniVO vo) {
		AddressbookAlumni addressbookAlumni = vo.getAddressbookAlumni();
		addressbookAlumni.setPublisher(vo.getLoginUser().getName());
		this.addressbookAlumniDAO.saveAddressbookAlumni(addressbookAlumni);
		return null;
	}
	@Override
	public String deleteAddressbookAlumni(AddressbookAlumniVO vo) {
		this.addressbookAlumniDAO.deleteAddressbookAlumni(vo.getAddressbookAlumni());
		return null;
	}
	@Override
	public String updateAddressbookAlumni(AddressbookAlumniVO vo) {
		AddressbookAlumni addressbookAlumniNew = vo.getAddressbookAlumni();
		addressbookAlumniNew.setPublisher(vo.getLoginUser().getName());
		addressbookAlumniNew.setCreateTime(null);
		this.addressbookAlumniDAO.mergeAddressbookAlumni(addressbookAlumniNew);
		return null;
	}
}
