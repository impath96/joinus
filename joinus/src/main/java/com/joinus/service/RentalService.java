package com.joinus.service;

import com.joinus.domain.PartnerPlacesVo;

public interface RentalService {
	
	// 제휴시설 상세보기
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no);

}
