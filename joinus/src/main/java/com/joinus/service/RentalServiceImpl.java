package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	public Integer pay(PaymentsVo vo) {
		return dao.pay(vo);
	}

	@Override
	public void place(RentalPlacesVo vo) {
		dao.place(vo);
	}
	
	
	
}
