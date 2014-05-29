package com.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.PublicDAO;
import com.model.anniversary.AnniversaryDonation;
import com.service.AnniversaryService;
import com.vo.bean.AnniversaryDonationVO;
import com.ynu.zjx.MyUtil;
import com.ynu.zjx.SuperJson;


@Component("anniversaryService")
public class AnniversaryServiceImpl implements AnniversaryService{
	private PublicDAO publicDAO;

	public PublicDAO getPublicDAO() {
		return publicDAO;
	}
	@Resource
	public void setPublicDAO(PublicDAO publicDAO) {
		this.publicDAO = publicDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findAnniversaryDonations(AnniversaryDonationVO vo) {
		SuperJson<AnniversaryDonation> superJson = new SuperJson<AnniversaryDonation>();
		String queryString=" AnniversaryDonation ";
		superJson.setRows((List<AnniversaryDonation>)this.publicDAO.findListByPage(vo.getRows()*(vo.getPage()-1), vo.getRows(),vo.getSort(),vo.getOrder(),queryString));
		superJson.setTotal(publicDAO.findListTotal(queryString));
		return MyUtil.toJson(superJson);
	}

	@Override
	public String saveAnniversaryDonation(AnniversaryDonationVO vo) {
		AnniversaryDonation model = vo.getAnniversaryDonation();
		model.setPublisher(vo.getLoginUser().getName());
		this.publicDAO.saveObject(model);
		return null;
	}

	@Override
	public String deleteAnniversaryDonation(AnniversaryDonationVO vo) {
		AnniversaryDonation model = vo.getAnniversaryDonation();
		this.publicDAO.deleteObject(model);
		return null;
	}

	@Override
	public String updateAnniversaryDonation(AnniversaryDonationVO vo) {
		AnniversaryDonation model = vo.getAnniversaryDonation();
		model.setPublisher(vo.getLoginUser().getName());
		this.publicDAO.updateObject(model);
		return null;
	}
	
}
