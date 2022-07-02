package com.joinus.persistence;


import java.util.List;

import com.joinus.domain.ClubMemberVo;
import com.joinus.domain.ClubTotalBean;

public interface ClubDao {
	
	
	//클럽회원 리스트
	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽리스트
	public List<ClubTotalBean> clubList(int interest_no);

	public List<ClubTotalBean> clubList();
	
	
	
	

}
