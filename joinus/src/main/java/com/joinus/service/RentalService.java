package com.joinus.service;

import java.util.List;

import com.joinus.domain.PartnerPlacesVo;

public interface RentalService {
	
	// 제휴시설 상세보기
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no);
	
	// 제휴시설 리스트(전체)
	public List<PartnerPlacesVo> getAllPartnerPlaceList();

}
