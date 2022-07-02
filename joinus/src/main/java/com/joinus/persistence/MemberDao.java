package com.joinus.persistence;


import com.joinus.domain.MemberVo;

public interface MemberDao {
	
	public MemberVo selectMember(String email);

	public void insertMember(MemberVo socialMember);
	
}
