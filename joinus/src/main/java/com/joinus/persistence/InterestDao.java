package com.joinus.persistence;

import java.util.List;

import com.joinus.domain.InterestVo;

public interface InterestDao {
	
	// 주 관심사 모두 출력
	List<InterestVo> selectInterestAll();
	
	// 

}
