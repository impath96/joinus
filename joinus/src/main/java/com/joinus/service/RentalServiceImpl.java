package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.PartnerPlacesVo;
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
	
	
	
}
