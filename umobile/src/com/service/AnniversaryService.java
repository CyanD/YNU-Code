package com.service;

import com.vo.bean.AnniversaryDonationVO;

public interface AnniversaryService {

	String findAnniversaryDonations(AnniversaryDonationVO vo);

	String saveAnniversaryDonation(AnniversaryDonationVO vo);

	String deleteAnniversaryDonation(AnniversaryDonationVO vo);

	String updateAnniversaryDonation(AnniversaryDonationVO vo);

}