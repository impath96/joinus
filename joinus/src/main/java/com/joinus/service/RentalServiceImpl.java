package com.joinus.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	
	
	
}
