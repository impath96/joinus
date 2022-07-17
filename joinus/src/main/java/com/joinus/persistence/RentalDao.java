package com.joinus.persistence;

import java.util.List;

import com.joinus.domain.PartnerPlacesVo;
import com.joinus.domain.PaymentsVo;
import com.joinus.domain.RentalPlacesVo;

public interface RentalDao {
	
	// 제휴시설 상세보기
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no);
	
	// 제휴시설 리스트(전체)
	public List<PartnerPlacesVo> getAllPartnerPlaceList();
	
	//결제
	public Integer pay(PaymentsVo vo);
	//결제 후 예약리스트 저장
	public void place(RentalPlacesVo vo);
	
}
