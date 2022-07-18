package com.joinus.persistence;

import java.util.List;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.PartnerPlacesVo;
import com.joinus.domain.PaymentsVo;
import com.joinus.domain.RentalPlacesVo;

public interface RentalDao {
	
	// 제휴시설 상세보기
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no);
	
	// 제휴시설 리스트(전체)
	public List<PartnerPlacesVo> getAllPartnerPlaceList();
	
	// 부산 (구) 리스트
	public List<LocationCityVo> getBusanGuList();
	
	// (구) 별 제휴시설리스트
	public List<PartnerPlacesVo> getCityPartnerPlaceList(String partner_place_address);
	
	// 장소유형, 위치 제휴시설리스트
	public List<PartnerPlacesVo> getPartnerPlaceList(String partner_place_type, String partner_place_address);
	
	// 장소유형별 제휴시설리스트
	public List<PartnerPlacesVo> getTypePartnerPlaceList(String partner_place_type);
	
	//결제
	public Integer pay(PaymentsVo vo);
	//결제 후 예약리스트 저장
	public void place(RentalPlacesVo vo);
	
}
