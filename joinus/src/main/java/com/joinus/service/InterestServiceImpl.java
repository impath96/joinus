package com.joinus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinus.domain.InterestVo;
import com.joinus.persistence.InterestDao;


@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	InterestDao interestDao;
	
	private static final Logger log = LoggerFactory.getLogger(InterestServiceImpl.class);

	@Override
	public List<InterestVo> selectInterestAll() {
		log.info("interest-service : 주 관심사 전체 조회");
		return interestDao.selectInterestAll();
	}

}