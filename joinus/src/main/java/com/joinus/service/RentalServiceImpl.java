package com.joinus.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.PartnerPlacesVo;
import com.joinus.domain.PaymentsVo;
import com.joinus.domain.RentalPlacesVo;
import com.joinus.persistence.RentalDao;

@Service
public class RentalServiceImpl implements RentalService {
	
	private static final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);
	
	@Inject
	private RentalDao dao;

	@Override
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no) {
		return dao.getPartnerPlaceContent(partner_place_no);
	}

	@Override
	public List<PartnerPlacesVo> getAllPartnerPlaceList() {
		return dao.getAllPartnerPlaceList();
	}

	@Override
	public List<LocationCityVo> getBusanGuList() {
		return dao.getBusanGuList();
	}

	@Override
	public List<PartnerPlacesVo> getCityPartnerPlaceList(String partner_place_address) {
		return dao.getCityPartnerPlaceList(partner_place_address);
	}

	@Override
	public List<PartnerPlacesVo> getPartnerPlaceList(String partner_place_type, String partner_place_address) {
		return dao.getPartnerPlaceList(partner_place_type, partner_place_address);
	}

	@Override
	public List<PartnerPlacesVo> getTypePartnerPlaceList(String partner_place_type) {
		return dao.getTypePartnerPlaceList(partner_place_type);
	}
	
	@Override
	public List<RentalPlacesVo> getRentalPlaceDate(int partner_place_no) {
		return dao.getRentalPlaceDate(partner_place_no);
	}
	
	@Override
	public List<Integer> getRentalTime(Date rental_date, Integer partner_place_no) {
		return dao.getRentalTime(rental_date, partner_place_no);
	}

	@Override
	public Integer pay(PaymentsVo vo) {
		return dao.pay(vo);
	}

	@Override
	public void insertPlaceBeforePay(Date rental_date, int rentaltimeno) {
		dao.insertPlaceBeforePay(rental_date, rentaltimeno);
	}

	@Override
	public RentalPlacesVo getLatelyRentalPlace() {
		return dao.getLatelyRentalPlace();
	}

	@Override
	public int getRentalPlaceCnt() {
		return dao.getRentalPlaceCnt();
	}

	@Override
	public void updateLatelyRentalPlace(String reservation_no, int member_no, int partner_place_no,
			int payment_no, int rental_places_no) {
		dao.updateLatelyRentalPlace(reservation_no, member_no, partner_place_no, payment_no, rental_places_no);
	}
	
	
	
}
