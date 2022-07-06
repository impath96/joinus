package com.joinus.service;

import java.util.List;

import com.joinus.domain.InterestVo;

public interface InterestService {
	
	// 주 관심사 전체 조회
	List<InterestVo> selectInterestAll();

}
